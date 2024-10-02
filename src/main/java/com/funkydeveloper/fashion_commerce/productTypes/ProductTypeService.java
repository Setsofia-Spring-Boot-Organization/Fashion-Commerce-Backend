package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductTypeService {

    ResponseEntity<Response<List<ProductType>>> createNewProduct(List<String> names);
}
