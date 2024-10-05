package com.example.fashion_commerce.product;

import com.example.fashion_commerce.product.requests.FilterProducts;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
@RequiredArgsConstructor
public class ProductPredicates {

    private final ProductRepository productRepository;

    public List<Product> globalProductFilter(FilterProducts filterProducts) {

        ListIterator<String> sizeIterator = filterProducts.getSizes().listIterator();
        ListIterator<String> categoryIterator = filterProducts.getCategories().listIterator();
        ListIterator<String> colorsIterator = filterProducts.getColors().listIterator();


        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct
                .type.eq(filterProducts.getType())
                .or(qProduct.sizes.contains(sizeIterator.next()))
                .or(qProduct.isAvailable.eq(filterProducts.isAvailable()))
                .or(qProduct.categories.contains(categoryIterator.next()))
                .or(qProduct.colors.contains(colorsIterator.next()))
                .or(qProduct.price.between(filterProducts.getStartPrice(), filterProducts.getEndPrice()));
        return (List<Product>) productRepository.findAll(predicate);
    }
}
