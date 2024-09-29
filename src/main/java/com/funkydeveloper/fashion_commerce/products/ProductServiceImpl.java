package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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
        Product createdProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<CreatedProductResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product created successfully")
                        .data(
                                new CreatedProductResponse(
                                        createdProduct
                                )
                        )
                        .build()
        );
    }
}
