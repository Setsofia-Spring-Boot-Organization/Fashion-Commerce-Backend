package com.example.fashion_commerce.product.responses;

import java.util.List;

public record ThisWeekProductsRes(
        String id,
        List<String> images,
        List<String> types,
        String name,
        Double price
) { }
