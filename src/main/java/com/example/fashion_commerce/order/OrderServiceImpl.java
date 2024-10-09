package com.example.fashion_commerce.order;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.requests.CreateOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public ResponseEntity<Response<Order>> createOrder(CreateOrder order) {



        return null;
    }
}
