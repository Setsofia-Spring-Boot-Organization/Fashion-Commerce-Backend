package com.example.fashion_commerce.product.productCategory;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productCategory.requests.CreateNewCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService{


    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ResponseEntity<Response<List<ProductCategory>>> createNewProductCategory(CreateNewCategory category) {

        List<ProductCategory> productCategories = new ArrayList<>();

        // verify the product type names
        Set<String> validCategories = getValidCategories(category);

        for (String name : validCategories) {
            productCategories.add(
                    new ProductCategory(name)
            );
        }

        List<ProductCategory> categories = productCategoryRepository.saveAll(productCategories);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<List<ProductCategory>>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product sizes(s) created successfully")
                        .data(categories)
                        .build()
        );
    }

    private Set<String> getValidCategories(CreateNewCategory category) {

        Set<String> validNames = new HashSet<>();
        List<ProductCategory> productCategories = productCategoryRepository.findAll();

        if (productCategories.isEmpty()) {
            validNames.addAll(category.names());
            return validNames;
        }

        List<String> existingNames = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            existingNames.add(productCategory.getCategory());
        }

        for (String name : category.names()) {
            for (String ignored : existingNames) {
                if (!existingNames.contains(name)) {
                    validNames.add(name);
                }
            }
        }

        return validNames;
    }



    @Override
    public ResponseEntity<Response<List<ProductCategory>>> getProductCategories() {

        List<ProductCategory> categories = productCategoryRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<ProductCategory>>builder()
                        .status(HttpStatus.OK.value())
                        .message("product categories")
                        .data(categories)
                        .build()
        );
    }
}
