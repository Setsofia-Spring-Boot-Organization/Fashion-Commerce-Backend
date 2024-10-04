package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductColorServiceImpl implements ProductColorService {


    @Override
    public ResponseEntity<Response<List<ProductColor>>> createNewProductColor(CreateProductColor productColor) {
        return null;
    }
}
