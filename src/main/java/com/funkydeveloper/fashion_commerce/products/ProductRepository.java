package com.funkydeveloper.fashion_commerce.products;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

    default List<Product> findAllFromLastSevenDays() {
        return findAll(ProductSpecification.getCollectionsFromLastSevenDays());
    }
}
