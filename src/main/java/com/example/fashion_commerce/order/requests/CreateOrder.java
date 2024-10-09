package com.example.fashion_commerce.order.requests;

public record CreateOrder(
        String email,
        String phone,
        String firstname,
        String lastname,
        String country,
        String region,
        String address,
        String city,
        String postalCode
) { }
