package com.funkydeveloper.fashion_commerce.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findProductsByOrderByCreatedAtDesc();

    List<Product> findAllByCreatedAtAfter(LocalDateTime localDateTime);

    List<Product> findAllByCategoriesContains(String gender);

    List<Product> findAllByNameContainingIgnoreCase(String product);

    List<Product> findAllByCreatedAtAfterAndCategoriesContains(LocalDateTime localDateTime, String gender);
}
