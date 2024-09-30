package com.funkydeveloper.fashion_commerce.products.responses;

import java.util.List;

public record GetNewCollectionResponse(
    String id,
    List<String> images
) { }
