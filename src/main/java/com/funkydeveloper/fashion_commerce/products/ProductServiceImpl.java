package com.funkydeveloper.fashion_commerce.products;

import org.springframework.stereotype.Service;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {


}
