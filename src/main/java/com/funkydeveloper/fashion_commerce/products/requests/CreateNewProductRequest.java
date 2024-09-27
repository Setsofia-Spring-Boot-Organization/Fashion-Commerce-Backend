package com.funkydeveloper.fashion_commerce.products.requests;

import java.util.List;

public record CreateNewProductRequest(
        String name,
        String price,
        List<String>size,
        List<String> color,
        List<String> images,
        boolean isAvailable,
        String description
) { }
