package com.example.fashion_commerce.product;

import com.example.fashion_commerce.product.productCategory.ProductCategoryRepository;
import com.example.fashion_commerce.product.productSize.ProductSizeRepository;
import com.example.fashion_commerce.product.requests.FilterProducts;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
@RequiredArgsConstructor
public class ProductPredicates {

    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public List<Product> globalProductFilter(FilterProducts filter) {


        ListIterator<String> sizeIterator = getDefaultSizes(filter).listIterator();
        ListIterator<String> categoryIterator = getDefaultCategories(filter).listIterator();
        ListIterator<String> colorsIterator = filter.getColors().listIterator();


        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct
                .type.eq(filter.getType())
                .or(qProduct.sizes.contains(sizeIterator.next()))
                .or(qProduct.isAvailable.eq(filter.isAvailable()))
                .or(qProduct.categories.contains(categoryIterator.next()))
                .or(qProduct.colors.contains(colorsIterator.next()))
                .or(qProduct.price.between(filter.getStartPrice(), filter.getEndPrice()));
        return (List<Product>) productRepository.findAll(predicate);
    }

    private List<String> getDefaultSizes(FilterProducts filter) {
        List<String> sizes = new ArrayList<>();
        if (filter.getSizes().isEmpty()) {
            productSizeRepository.findAll().forEach(
                    productSize -> sizes.add(productSize.getSize())
            );
            return sizes;
        }
        return filter.getSizes();
    }

    private List<String> getDefaultCategories(FilterProducts filter) {
        List<String> categories = new ArrayList<>();
        if (filter.getCategories().isEmpty()) {
            productCategoryRepository.findAll().forEach(
                    productCategory -> categories.add(productCategory.getCategory())
            );
            return categories;
        }
        return filter.getCategories();
    }
}
