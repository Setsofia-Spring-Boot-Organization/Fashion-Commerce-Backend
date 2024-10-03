package com.funkydeveloper.fashion_commerce.product;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Component
public class ProductPredicates {

    public static Predicate<Product> getNewCollectionsPredicate() {
        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(7);

        return product -> product.getCreatedAt().isAfter(
                lastSevenDays
        );
    }
}
