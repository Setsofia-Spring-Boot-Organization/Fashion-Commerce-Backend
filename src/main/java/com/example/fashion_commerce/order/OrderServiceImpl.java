package com.example.fashion_commerce.order;

import com.example.fashion_commerce.exception.Error;
import com.example.fashion_commerce.exception.FashionCommerceException;
import com.example.fashion_commerce.exception.Message;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.CreateOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(rollbackFor = {FashionCommerceException.class, Exception.class})
    @Override
    public ResponseEntity<Response<Order>> createOrder(CreateOrder createOrder) {

        Order order = createdOrder(createOrder);

        Response<Order> orderResponse = new Response<>(
                HttpStatus.CREATED.value(),
                "order created successfully",
                order
        );

        return null;
    }

    private Order createdOrder(CreateOrder createOrder) {
        ContactInfo contactInfo = createContactInfo(createOrder);
        ShippingAddress shippingAddress = createShippingAddress(createOrder);

        Order order = new Order(
                contactInfo,
                shippingAddress,
                createOrder.productIDs()
        );

        try {
            return orderRepository.save(order);

        } catch (Exception exception) {
            throw new FashionCommerceException(Error.ERROR_SAVING_DATA, new Throwable(Message.CANNOT_PLACE_ORDER.label));
        }
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
