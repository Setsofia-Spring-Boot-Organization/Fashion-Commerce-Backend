package com.example.fashion_commerce.product.requests;

import com.example.fashion_commerce.product.productCategory.requests.CreateNewCategory;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import com.example.fashion_commerce.product.productSize.requests.CreateProductSize;
import com.example.fashion_commerce.product.productType.requests.CreateProductType;

public record CreateFilterOptions(
        CreateNewCategory category,
        CreateProductColor color,
        CreateProductSize size,
        CreateProductType type
) { }
