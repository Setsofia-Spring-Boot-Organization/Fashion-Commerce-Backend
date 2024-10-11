package com.example.fashion_commerce.product.responses;

import com.example.fashion_commerce.product.productCategory.ProductCategory;
import com.example.fashion_commerce.product.productColor.ProductColor;
import com.example.fashion_commerce.product.productSize.ProductSize;
import com.example.fashion_commerce.product.productType.ProductType;

import java.util.List;

public record FilterOptions(
        List<ProductCategory> categories,
        List<ProductColor> colors,
        List<ProductSize> sizes,
        List<ProductType> productTypes
) {
}
