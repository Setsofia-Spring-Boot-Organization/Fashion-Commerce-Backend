package com.example.fashion_commerce.product.productCategory;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productCategory.requests.CreateNewCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductCategoryService {
    ResponseEntity<Response<List<ProductCategory>>> createNewProductCategory(CreateNewCategory category);

    ResponseEntity<Response<List<ProductCategory>>> getProductCategories();
}
