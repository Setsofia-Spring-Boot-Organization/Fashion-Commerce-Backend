package com.example.fashion_commerce.product.responses;


import com.example.fashion_commerce.product.Product;

import java.util.List;

public record AllProductsRes (
        List<Product> products,
        int available,
        int unavailable
) { }
