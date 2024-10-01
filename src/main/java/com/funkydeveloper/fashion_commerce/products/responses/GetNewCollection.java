package com.funkydeveloper.fashion_commerce.products.responses;

import java.util.List;

public record GetNewCollection(
    String id,
    List<String> images
) { }
