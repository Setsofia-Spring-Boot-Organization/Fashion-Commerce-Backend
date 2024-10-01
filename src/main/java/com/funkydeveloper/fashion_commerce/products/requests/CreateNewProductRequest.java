package com.funkydeveloper.fashion_commerce.products.requests;

import java.util.List;

public record CreateNewProductRequest(
        String name,
        String price,
        String type,
        List<String>sizes,
        List<String> colors,
        List<String> images,
        List<String> categories,
        String description
) { }
