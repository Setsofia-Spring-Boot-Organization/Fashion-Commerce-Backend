package com.example.fashion_commerce.order;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.requests.CreateOrder;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PostMapping
    public ResponseEntity<Response<Order>> createOrder(
            @RequestBody CreateOrder order
    ) {
        return orderService.createOrder(order);
    }


    @GetMapping
    public ResponseEntity<Response<List<Order>>> getOrders(
            @RequestParam boolean all,
            @RequestParam RequestOrderStatus status
    ) {
        return orderService.getOrders(all, status);
    }
}
