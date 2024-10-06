package com.example.fashion_commerce.product.productCategory;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productCategory.requests.CreateNewCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<Response<List<ProductCategory>>> createNewProductCategory(
            @RequestBody CreateNewCategory category
    ) {
        return categoryService.createNewProductCategory(category);
    }



    @GetMapping
    public ResponseEntity<Response<List<ProductCategory>>> getProductCategories() {
        return categoryService.getProductCategories();
    }
}
