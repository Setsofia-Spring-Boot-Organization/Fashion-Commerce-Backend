package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.requests.CreateProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-type")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity<Response<List<ProductType>>> createNewProductType(
            @RequestBody CreateProductType productType
    ) {
        return productTypeService.createNewProduct(productType);
    }



    @GetMapping
    public ResponseEntity<Response<List<ProductType>>> getProductTypes() {
        return productTypeService.getProductTypes();
    }
}
