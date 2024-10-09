package com.example.fashion_commerce.order;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.requests.CreateOrder;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<Response<Order>> createOrder(CreateOrder order);
}
