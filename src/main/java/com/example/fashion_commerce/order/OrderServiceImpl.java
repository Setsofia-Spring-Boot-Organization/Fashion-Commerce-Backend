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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Set<String> invalidIDs = validateIDs(createOrder.productIDs());
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

    private Set<String> validateIDs(List<String> ids) {
        List<Product> products = productRepository.findAll();
        Set<String> inValidIDs = new HashSet<>();

        for (String id : ids) {
            boolean isValid = false;

            for (Product product : products) {
                if (id.equals(product.getId())) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                inValidIDs.add(id);
            }
        }
        return inValidIDs;
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
}
