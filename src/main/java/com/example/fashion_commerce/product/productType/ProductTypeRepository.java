package com.example.fashion_commerce.product.productType;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductTypeRepository extends MongoRepository<ProductType, String>, QuerydslPredicateExecutor<ProductType> {

}
