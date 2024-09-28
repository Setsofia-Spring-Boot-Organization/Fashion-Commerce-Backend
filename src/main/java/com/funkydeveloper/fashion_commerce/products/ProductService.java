package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    /**
     * This method creates a new product based on the provided details in the request.
     *
     * @param request the {@link CreateNewProductRequest} object containing the details of the product to be created
     *        such as name, price, available sizes, colors, images, availability status, and description
     * @return a {@link ResponseEntity} containing the response with the status of the product creation
     */
    ResponseEntity<Response<CreatedProductResponse>> createNewProduct(CreateNewProductRequest request);
}
