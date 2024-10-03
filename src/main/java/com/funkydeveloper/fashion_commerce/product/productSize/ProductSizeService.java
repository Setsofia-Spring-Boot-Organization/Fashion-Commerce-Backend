package com.funkydeveloper.fashion_commerce.product.productSize;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.productSize.requests.CreateProductSize;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductSizeService {
    ResponseEntity<Response<List<ProductSize>>> createNewProductSize(CreateProductSize productSize);
}
