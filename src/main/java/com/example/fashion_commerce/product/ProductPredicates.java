package com.example.fashion_commerce.product;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPredicates {

    private final ProductRepository productRepository;

    public List<Product> globalProductFilter() {
        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct.name.startsWith("D");

        return (List<Product>) productRepository.findAll(predicate);
    }
}
