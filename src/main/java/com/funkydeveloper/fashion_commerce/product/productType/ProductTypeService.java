package com.funkydeveloper.fashion_commerce.product.productType;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.productType.requests.CreateProductType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductTypeService {

    ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType productType);

    ResponseEntity<Response<List<ProductType>>> getProductTypes();
}
