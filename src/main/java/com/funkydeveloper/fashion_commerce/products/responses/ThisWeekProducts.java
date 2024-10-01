package com.funkydeveloper.fashion_commerce.products.responses;

import java.util.List;

public record ThisWeekProducts(
        String id,
        List<String> images,
        List<String> categories,
        String name,
        String price
) { }
