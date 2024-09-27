package com.funkydeveloper.fashion_commerce.collection.products;

import org.springframework.stereotype.Service;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {


}
