package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductColorService {
    ResponseEntity<Response<List<ProductColor>>> createNewProductColor(CreateProductColor productColor);
}
