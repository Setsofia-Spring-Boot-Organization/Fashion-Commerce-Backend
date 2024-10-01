package com.funkydeveloper.fashion_commerce.products;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByCreatedAtAfter(LocalDateTime localDateTime);

    List<Product> findAllByGendersContains(String gender);
}
