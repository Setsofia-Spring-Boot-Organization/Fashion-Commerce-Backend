package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-color")
@RequiredArgsConstructor
public class ProductColorController {

    private final ProductColorService colorService;



    @PostMapping
    public ResponseEntity<Response<List<ProductColor>>> createNewProductColor(
            @RequestBody CreateProductColor productColor
    ) {
        return colorService.createNewProductColor(productColor);
    }
}
