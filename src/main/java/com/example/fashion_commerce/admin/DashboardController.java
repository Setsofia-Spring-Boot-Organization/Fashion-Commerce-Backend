package com.example.fashion_commerce.admin;

import com.example.fashion_commerce.admin.responses.OrderAnalytics;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/admin/dashboard")
public class DashboardController {
    private final OrderService orderService;

    public DashboardController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("order/analytics")
    public ResponseEntity<Response<OrderAnalytics>> getOrderAnalytics() {
        return orderService.getOrderAnalytics();
    }
}
