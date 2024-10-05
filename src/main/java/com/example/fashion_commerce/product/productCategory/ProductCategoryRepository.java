package com.example.fashion_commerce.product.productCategory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String>, QuerydslPredicateExecutor<ProductCategory> { }
