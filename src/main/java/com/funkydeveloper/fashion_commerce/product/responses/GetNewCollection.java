package com.funkydeveloper.fashion_commerce.product.responses;

import java.util.List;

public record GetNewCollection(
    String id,
    List<String> images
) { }
