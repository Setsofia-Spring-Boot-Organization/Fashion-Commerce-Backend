package com.funkydeveloper.fashion_commerce.products.requests;

import java.util.List;

public record CreateNewProductRequest(
        String name,
        String price,
        List<String> categories,
        List<String>sizes,
        List<String> colors,
        List<String> images,
        List<String> genders,
        String description
) { }
