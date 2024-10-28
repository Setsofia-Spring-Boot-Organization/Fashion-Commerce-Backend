package com.example.fashion_commerce.order;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.requests.CreateOrder;
import com.example.fashion_commerce.order.requests.RequestOrderStatus;
import com.example.fashion_commerce.order.responses.OrderDetails;
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
            @RequestParam(required = false) RequestOrderStatus status
    ) {
        return orderService.getOrders(all, status);
    }



    @GetMapping(path = "/get")
    public ResponseEntity<Response<OrderDetails>> getOrder(
            @RequestParam String id
    ) {
        return orderService.getOrder(id);
    }



    @PatchMapping("update/{order-id}")
    public ResponseEntity<Response<Order>> updateOrder(
            @PathVariable("order-id") String id,
            @RequestParam String status,
            @RequestBody String notes
    ) {
        return orderService.updateOrder(id, status, notes);
    }
}
