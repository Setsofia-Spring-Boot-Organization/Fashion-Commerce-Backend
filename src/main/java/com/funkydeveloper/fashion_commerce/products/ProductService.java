package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<Response<?>> createNewProduct(String id, CreateNewProductRequest request);
}
