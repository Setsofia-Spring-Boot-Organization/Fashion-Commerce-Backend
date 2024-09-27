package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {


    @Override
    public ResponseEntity<Response<CreatedProductResponse>> createNewProduct(String id, CreateNewProductRequest request) {
        return null;
    }
}
