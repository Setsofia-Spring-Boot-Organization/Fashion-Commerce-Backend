package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-color")
public class ProductColorController {

    private final ProductColorService colorService;

    public ProductColorController(ProductColorService colorService) {
        this.colorService = colorService;
    }


    @PostMapping
    public ResponseEntity<Response<List<ProductColor>>> createNewProductColor(
            @RequestBody CreateProductColor productColor
    ) {
        return colorService.createNewProductColor(productColor);
    }



    @GetMapping
    public ResponseEntity<Response<List<ProductColor>>> getProductColors() {

        return colorService.getProductColors();
    }
}
