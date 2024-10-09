package com.example.fashion_commerce.order;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.checkout.ContactInfo;
import com.example.fashion_commerce.order.checkout.ShippingAddress;
import com.example.fashion_commerce.order.requests.CreateOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public ResponseEntity<Response<Order>> createOrder(CreateOrder createOrder) {

        ContactInfo contactInfo = createContactInfo(createOrder);
        ShippingAddress shippingAddress = createShippingAddress(createOrder);

        Order order = new Order(
                contactInfo,
                shippingAddress,
                createOrder.productIDs()
        );

        return null;
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
