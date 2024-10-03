package com.funkydeveloper.fashion_commerce.product.productSize;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.productSize.requests.CreateProductSize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-sizes")
@RequiredArgsConstructor
public class ProductSizeController {

    private final ProductSizeService productSizeService;



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
