package com.example.fashion_commerce.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String>, QuerydslPredicateExecutor<Product> {

    List<Product> findProductsByOrderByCreatedAtDesc();

    Product findProductById(String id);

    List<Product> findAllByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime localDateTime);

    List<Product> findAllByCategoriesContainsIgnoreCase(String gender);

    List<Product> findAllByNameContainingIgnoreCase(String product);

    List<Product> findAllByCreatedAtAfterAndCategoriesContainsIgnoreCase(LocalDateTime localDateTime, String gender);
}
