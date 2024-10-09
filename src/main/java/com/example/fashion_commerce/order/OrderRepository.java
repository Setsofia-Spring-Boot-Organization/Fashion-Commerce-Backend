package com.example.fashion_commerce.order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends MongoRepository<Order, String>, QuerydslPredicateExecutor<Order> { }
