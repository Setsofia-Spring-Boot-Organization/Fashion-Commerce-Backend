package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.exception.Error;
import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.exception.Message;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<Response<CreatedProductResponse>> createNewProduct(CreateNewProductRequest request) {

        // sanitize the incoming request
        sanitizeRequest(request);


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

    private void sanitizeRequest(CreateNewProductRequest request) {
        List<String> emptyFields = new ArrayList<>();

        if (request.name().isEmpty() || request.name().isBlank())
            emptyFields.add("name");

        if (!emptyFields.isEmpty())
            throw new FashionCommerceException(
                    Error.NO_EMPTY_FIELDS_ALLOWED,
                    new Throwable(Message.THE_FOLLOWING_FIELDS_ARE_EMPTY.label + emptyFields)
            );
    }
}
