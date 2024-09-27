package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public record ProductController(ProductService productService) {

    @PostMapping("{admin-id}")
    public ResponseEntity<Response<?>> createNewProduct(
            @PathVariable("admin-id") String id,
            @RequestBody CreateNewProductRequest request
    ) throws FashionCommerceException {
        return productService.createNewProduct(id, request);
    }
    
}
