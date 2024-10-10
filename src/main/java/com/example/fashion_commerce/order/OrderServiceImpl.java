package com.example.fashion_commerce.order;

import com.example.fashion_commerce.exception.Error;
import com.example.fashion_commerce.exception.FashionCommerceException;
import com.example.fashion_commerce.exception.Message;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.CreateOrder;
import com.example.fashion_commerce.product.Product;
import com.example.fashion_commerce.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Response<Order>> createOrder(CreateOrder createOrder) {

        Order order = createdOrder(createOrder);

        Response<Order> orderResponse = new Response<>(
                HttpStatus.CREATED.value(),
                "order created successfully",
                order
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    private Order createdOrder(CreateOrder createOrder) {

        List<String> invalidIDs = validateIDs(createOrder.productIDs());
        if (!invalidIDs.isEmpty()) {
            throw new FashionCommerceException(Error.INVALID_PRODUCT_IDS, new Throwable(Message.THE_FOLLOWING_IDS_DOES_NOT_EXIST.label + ": " + invalidIDs.stream().sorted().toList()));
        }

        ContactInfo contactInfo = createContactInfo(createOrder);
        ShippingAddress shippingAddress = createShippingAddress(createOrder);

        Order order = new Order(
                contactInfo,
                shippingAddress,
                createOrder.productIDs(),
                OrderStatus.CREATED
        );

        try {
            return orderRepository.save(order);

        } catch (Exception exception) {
            throw new FashionCommerceException(Error.ERROR_SAVING_DATA, new Throwable(Message.CANNOT_PLACE_ORDER.label));
        }
    }

    private List<String> validateIDs(List<String> ids) {
        List<String> productIDs = productRepository.findAll().stream().map(Product::getId).toList();
        ids.removeAll(productIDs);

        return ids;
    }

    private ContactInfo createContactInfo(CreateOrder createOrder) {
        return new ContactInfo(
                createOrder.email(),
                createOrder.phone()
        );
    }

    private ShippingAddress createShippingAddress(CreateOrder createOrder) {
        return new ShippingAddress(
                createOrder.firstname(),
                createOrder.lastname(),
                createOrder.country(),
                createOrder.region(),
                createOrder.address(),
                createOrder.city(),
                createOrder.postalCode()
        );
    }


    @Override
    public ResponseEntity<Response<List<Order>>> getOrders(boolean all, RequestOrderStatus status) {
        List<Order> orders;

        if (all) {
            orders = orderRepository.findAll();
        } else {
            if (!isValidOrderStatus(status.getStatus())) {
                throw new FashionCommerceException(Error.INVALID_ORDER_STATUS, new Throwable(Message.THE_REQUESTED_ORDER_STATUS_IS_INVALID.label));
            }

            orders = orderRepository.findOrderByStatus(status); //get the orders with the specified status
        }

        Response<List<Order>> orderResponse = new Response<>(
                HttpStatus.OK.value(),
                "all orders",
                orders,
                String.valueOf(orders.size())
        );

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    private boolean isValidOrderStatus(String status) {

        return Arrays.stream(OrderStatus.values()).anyMatch(orderStatus -> orderStatus.name().equals(status.toUpperCase()));
    }
}
