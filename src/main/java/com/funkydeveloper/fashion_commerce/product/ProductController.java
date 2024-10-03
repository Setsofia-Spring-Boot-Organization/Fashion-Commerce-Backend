package com.funkydeveloper.fashion_commerce.product;

import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.product.responses.AllProductsRes;
import com.funkydeveloper.fashion_commerce.product.responses.CreatedProductRes;
import com.funkydeveloper.fashion_commerce.product.responses.GetNewCollectionRes;
import com.funkydeveloper.fashion_commerce.product.responses.ThisWeekProductsRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public record ProductController(ProductService productService) {

    @PostMapping()
    public ResponseEntity<Response<CreatedProductRes>> createNewProduct(
            @RequestBody CreateNewProductRequest request
    ) throws FashionCommerceException {
        return productService.createNewProduct(request);
    }



    @GetMapping("all")
    public ResponseEntity<Response<AllProductsRes>> getProducts() {
        return productService.getProducts();
    }



    @GetMapping("new-collection")
    public ResponseEntity<Response<List<GetNewCollectionRes>>> getNewCollections() {
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



    @GetMapping
    public ResponseEntity<Response<List<Product>>> searchProduct(
            @RequestParam String product
    ) {
        return productService.searchProduct(product);
    }



    @GetMapping("new-this-week")
    public ResponseEntity<Response<List<ThisWeekProductsRes>>> getThisWeekProducts() {
        return productService.getThisWeekProducts();
    }



    @GetMapping("filter/all")
    public ResponseEntity<Response<List<Product>>> filterProductsFromLastYear(
            @RequestParam boolean all,
            @RequestParam String gender
    ) {
        return productService.filterProductsFromLastYear(all, gender);
    }
}
