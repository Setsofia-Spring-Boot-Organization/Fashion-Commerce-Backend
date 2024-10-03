package com.funkydeveloper.fashion_commerce.product.productCategory.requests;

import java.util.List;

public record CreateNewCategory(
    List<String> names
) { }
