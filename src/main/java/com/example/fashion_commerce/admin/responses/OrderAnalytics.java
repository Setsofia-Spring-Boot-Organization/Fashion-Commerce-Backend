package com.example.fashion_commerce.admin.responses;

public record OrderAnalytics(
    double totalOrder,
    double pendingOrders,
    double deliveredOrders,
    double cancelledOrders
) { }
