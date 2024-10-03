package com.example.fashion_commerce.product.responses;

import java.util.List;

public record GetNewCollectionRes(
    String id,
    List<String> images
) { }
