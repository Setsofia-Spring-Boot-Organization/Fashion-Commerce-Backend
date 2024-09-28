package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {


    @Override
    public ResponseEntity<Response<CreatedProductResponse>> createNewProduct(CreateNewProductRequest request) {

        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .sizes(request.sizes())
                .colors(request.colors())
                .images(request.images())
                .isAvailable(true)
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        //save the product
        productRepository.save(product);

        return null;
    }
}
