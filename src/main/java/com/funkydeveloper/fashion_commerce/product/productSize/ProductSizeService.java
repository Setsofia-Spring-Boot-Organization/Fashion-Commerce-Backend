package com.funkydeveloper.fashion_commerce.product.productSize;

import com.funkydeveloper.fashion_commerce.generics.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductSizeService {
    ResponseEntity<Response<List<ProductSize>>> createNewProductSize();
}
