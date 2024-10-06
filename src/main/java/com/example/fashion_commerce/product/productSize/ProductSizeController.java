package com.example.fashion_commerce.product.productSize;


import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productSize.requests.CreateProductSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-sizes")
public class ProductSizeController {

    private final ProductSizeService productSizeService;

    @Autowired
    public ProductSizeController(ProductSizeService productSizeService) {
        this.productSizeService = productSizeService;
    }


    @PostMapping
    public ResponseEntity<Response<List<ProductSize>>> createNewProductSize(
            @RequestBody CreateProductSize productSize
    ) {
        return productSizeService.createNewProductSize(productSize);
    }



    @GetMapping
    public ResponseEntity<Response<List<ProductSize>>> getAllProductSizes() {
        return productSizeService.getAllProductSizes();
    }
}
