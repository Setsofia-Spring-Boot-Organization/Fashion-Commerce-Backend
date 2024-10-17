package com.example.fashion_commerce.product.requests;

import java.util.List;

public record CreateFilterOptions(
        List<String> categories,
        List<String> colors,
        List<String> sizes,
        List<String> types
) { }
