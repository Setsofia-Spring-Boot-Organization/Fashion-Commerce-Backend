package com.example.fashion_commerce.product.productColor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductColorRepository extends MongoRepository<ProductColor, String>, QuerydslPredicateExecutor<ProductColor> { }
