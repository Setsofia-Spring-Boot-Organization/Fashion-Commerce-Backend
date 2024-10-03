package com.funkydeveloper.fashion_commerce.product.productCategory;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.productCategory.requests.CreateNewCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService categoryService;



    @PostMapping
    public ResponseEntity<Response<List<ProductCategory>>> createNewProductCategory(
            @RequestBody CreateNewCategory category
    ) {
        return categoryService.createNewProductCategory(category);
    }
}
