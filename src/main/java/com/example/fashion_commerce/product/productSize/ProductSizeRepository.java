package com.example.fashion_commerce.product.productSize;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductSizeRepository extends MongoRepository<ProductSize, String>, QuerydslPredicateExecutor<ProductSize> { }
