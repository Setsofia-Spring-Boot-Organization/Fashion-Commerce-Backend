package com.funkydeveloper.fashion_commerce.product.productSize;

import com.funkydeveloper.fashion_commerce.generics.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {

    @Override
    public ResponseEntity<Response<List<ProductSize>>> createNewProductSize() {
        return null;
    }
}
