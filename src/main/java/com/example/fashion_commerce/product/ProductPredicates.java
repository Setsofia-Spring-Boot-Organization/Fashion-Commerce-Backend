package com.example.fashion_commerce.product;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ListIterator;

@Component
@RequiredArgsConstructor
public class ProductPredicates {

    private final ProductRepository productRepository;

    public List<Product> globalProductFilter(
            String type,
            List<String> sizes,
            boolean isAvailable,
            List<String> categories,
            List<String> colors,
            String startPrice,
            String endPrice
    ) {

        ListIterator<String> sizeIterator = sizes.listIterator();
        ListIterator<String> categoryIterator = categories.listIterator();
        ListIterator<String> colorsIterator = colors.listIterator();


        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct
                .type.eq(type)
                .or(qProduct.sizes.contains(sizeIterator.next()))
                .or(qProduct.isAvailable.eq(isAvailable))
                .or(qProduct.categories.contains(categoryIterator.next()))
                .or(qProduct.colors.contains(colorsIterator.next()))
                .or(qProduct.price.between(startPrice, endPrice));
        return (List<Product>) productRepository.findAll(predicate);
    }
}
