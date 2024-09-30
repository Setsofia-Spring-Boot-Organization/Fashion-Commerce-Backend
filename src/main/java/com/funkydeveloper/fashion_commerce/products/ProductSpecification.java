package com.funkydeveloper.fashion_commerce.products;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductSpecification {

    public static Specification<Product> getCollectionsFromLastSevenDays() {
        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(7);

        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get("createdAt"), lastSevenDays
        );
    }
}
