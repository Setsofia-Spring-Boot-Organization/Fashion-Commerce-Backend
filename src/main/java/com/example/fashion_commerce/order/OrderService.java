package com.example.fashion_commerce.order;

import com.example.fashion_commerce.admin.responses.OrderAnalytics;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.requests.CreateOrder;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;
import com.example.fashion_commerce.order.requests.UpdateOrder;
import com.example.fashion_commerce.order.responses.OrderDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<Response<Order>> createOrder(CreateOrder order);

    ResponseEntity<Response<List<Order>>> getOrders(boolean all, RequestOrderStatus status);

    ResponseEntity<Response<OrderDetails>> getOrder(String id);

    ResponseEntity<Response<Order>> updateOrder(String id, String status, UpdateOrder notes);

    ResponseEntity<Response<OrderAnalytics>> getOrderAnalytics();
}
