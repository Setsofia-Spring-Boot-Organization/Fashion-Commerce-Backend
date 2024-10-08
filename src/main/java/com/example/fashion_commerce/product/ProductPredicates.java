package com.example.fashion_commerce.product;

import com.example.fashion_commerce.product.productCategory.ProductCategoryRepository;
import com.example.fashion_commerce.product.productColor.ProductColorRepository;
import com.example.fashion_commerce.product.productSize.ProductSizeRepository;
import com.example.fashion_commerce.product.requests.FilterProducts;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
public class ProductPredicates {

    private static final Logger log = LoggerFactory.getLogger(ProductPredicates.class);
    private final ProductSizeRepository productSizeRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductColorRepository productColorRepository;
    private final ProductRepository productRepository;

    public ProductPredicates(ProductSizeRepository productSizeRepository, ProductCategoryRepository productCategoryRepository, ProductColorRepository productColorRepository, ProductRepository productRepository) {
        this.productSizeRepository = productSizeRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productColorRepository = productColorRepository;
        this.productRepository = productRepository;
    }

    public List<Product> globalProductFilter(FilterProducts filter) {


        ListIterator<String> sizeIterator = getDefaultSizes(filter).listIterator();
        ListIterator<String> categoryIterator = getDefaultCategories(filter).listIterator();
        ListIterator<String> colorsIterator = getDefaultColors(filter).listIterator();

        List<String> sizes = new ArrayList<>();
        sizeIterator.forEachRemaining(sizes::add);

        List<String> categories = new ArrayList<>();
        categoryIterator.forEachRemaining(categories::add);

        List<String> colors = new ArrayList<>();
        colorsIterator.forEachRemaining(colors::add);

        if (filter.getStartPrice() == null || filter.getStartPrice().isEmpty()) {
            filter.setStartPrice("0");
        }
        if (filter.getEndPrice() == null || filter.getEndPrice().isEmpty()) {
            filter.setEndPrice("100000");
        }

        log.info("the value from the filter: {}", filter);

        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct.isAvailable.eq(filter.isAvailable());
//                .and(qProduct.isAvailable.eq(filter.isAvailable()));
        return (List<Product>) productRepository.findAll(predicate);
    }

    private List<String> getDefaultSizes(FilterProducts filter) {
        List<String> sizes = new ArrayList<>();
        if (filter.getSizes() == null) {
            productSizeRepository.findAll().forEach(
                    productSize -> sizes.add(productSize.getSize())
            );
            return sizes;
        }
        return filter.getSizes();
    }

    private List<String> getDefaultCategories(FilterProducts filter) {
        List<String> categories = new ArrayList<>();
        if (filter.getCategories() == null || filter.getCategories().isEmpty()) {
            productCategoryRepository.findAll().forEach(
                    productCategory -> categories.add(productCategory.getCategory())
            );
            return categories;
        }
        return filter.getCategories();
    }

    private List<String> getDefaultColors(FilterProducts filter) {
        List<String> colors = new ArrayList<>();
        if (filter.getColors() == null) {
            productColorRepository.findAll().forEach(
                    productColor -> colors.add(productColor.getColor())
            );
            return colors;
        }
        return filter.getColors();
    }
}
