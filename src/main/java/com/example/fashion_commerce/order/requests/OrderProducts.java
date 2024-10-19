package com.example.fashion_commerce.order.requests;

import com.example.fashion_commerce.product.Product;

public record OrderProducts (
        Product product,
        int quantity
) { }
