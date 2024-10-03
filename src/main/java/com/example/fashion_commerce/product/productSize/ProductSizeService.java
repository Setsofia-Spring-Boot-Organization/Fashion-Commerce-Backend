package com.example.fashion_commerce.product.productSize;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productSize.requests.CreateProductSize;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductSizeService {
    ResponseEntity<Response<List<ProductSize>>> createNewProductSize(CreateProductSize productSize);

    ResponseEntity<Response<List<ProductSize>>> getAllProductSizes();
}
