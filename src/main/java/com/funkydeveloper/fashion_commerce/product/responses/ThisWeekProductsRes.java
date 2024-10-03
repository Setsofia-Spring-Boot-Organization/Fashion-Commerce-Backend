package com.funkydeveloper.fashion_commerce.product.responses;

import java.util.List;

public record ThisWeekProductsRes(
        String id,
        List<String> images,
        String type,
        String name,
        String price
) { }
