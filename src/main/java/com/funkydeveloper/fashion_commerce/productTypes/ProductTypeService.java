package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.requests.CreateProductType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductTypeService {

    ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType productType);
}
