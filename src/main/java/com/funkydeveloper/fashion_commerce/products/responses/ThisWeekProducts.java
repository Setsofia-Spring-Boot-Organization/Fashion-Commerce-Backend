package com.funkydeveloper.fashion_commerce.products.responses;

import java.util.List;

public record ThisWeekProducts(
        String id,
        List<String> images,
        String type,
        String name,
        String price
) { }
