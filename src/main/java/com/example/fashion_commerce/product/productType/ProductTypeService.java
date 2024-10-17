package com.example.fashion_commerce.product.productType;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productType.requests.CreateProductType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductTypeService {

    ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType productType);

    ResponseEntity<Response<List<ProductType>>> getProductTypes();

    void saveTypes(CreateProductType types);
}
