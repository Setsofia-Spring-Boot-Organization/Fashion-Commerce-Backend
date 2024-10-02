package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.responses.CreatedProductTypes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductTypeService {

    ResponseEntity<Response<CreatedProductTypes>> createNewProduct(List<String> names);
}
