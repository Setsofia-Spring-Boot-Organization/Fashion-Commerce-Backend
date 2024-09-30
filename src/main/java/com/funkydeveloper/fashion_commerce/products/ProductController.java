package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import com.funkydeveloper.fashion_commerce.products.responses.GetNewCollectionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public record ProductController(ProductService productService) {

    @PostMapping()
    public ResponseEntity<Response<CreatedProductResponse>> createNewProduct(
            @RequestBody CreateNewProductRequest request
    ) throws FashionCommerceException {
        return productService.createNewProduct(request);
    }


    @GetMapping
    public ResponseEntity<Response<GetNewCollectionResponse>> getNewCollections() {
        return productService.getNewCollections();
    }
}
