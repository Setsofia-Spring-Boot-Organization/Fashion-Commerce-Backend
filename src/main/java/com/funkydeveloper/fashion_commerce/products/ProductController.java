package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProductResponse;
import com.funkydeveloper.fashion_commerce.products.responses.GetNewCollectionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public record ProductController(ProductService productService) {

    @PostMapping()
    public ResponseEntity<Response<CreatedProductResponse>> createNewProduct(
            @RequestBody CreateNewProductRequest request
    ) throws FashionCommerceException {
        return productService.createNewProduct(request);
    }

    @GetMapping("new-collection")
    public ResponseEntity<Response<List<GetNewCollectionResponse>>> getNewCollections() {
        return productService.getNewCollections();
    }

    @GetMapping("{product-id}")
    public ResponseEntity<Response<Product>> getProduct(
            @PathVariable("product-id") String id
    ) {
        return productService.getProduct(id);
    }

    @GetMapping("filter/gender")
    public ResponseEntity<Response<List<Product>>> filterProductsByGender(
            @RequestParam String gender
    ) {
        return productService.filterProductsByGender(gender);
    }
}
