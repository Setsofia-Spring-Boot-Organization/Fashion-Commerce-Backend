package com.example.fashion_commerce.product.productType;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productType.requests.CreateProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-type")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }


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
