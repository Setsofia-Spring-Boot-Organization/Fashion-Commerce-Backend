package com.example.fashion_commerce.admin.responses;

public record OrderAnalytics(
    int totalOrder,
    int pendingOrders,
    int deliveredOrders,
    int cancelledOrders
) { }
