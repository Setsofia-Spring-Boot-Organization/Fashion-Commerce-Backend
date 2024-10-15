package com.example.fashion_commerce.product;

import com.example.fashion_commerce.product.productCategory.ProductCategoryRepository;
import com.example.fashion_commerce.product.productColor.ProductColorRepository;
import com.example.fashion_commerce.product.productSize.ProductSizeRepository;
import com.example.fashion_commerce.product.requests.FilterProducts;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
public class ProductPredicates {

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

        if (filter.getEndPrice() == 0) {
            filter.setEndPrice(1000);
        }

        QProduct qProduct = new QProduct("product");
        Predicate predicate = qProduct
                .type.equalsIgnoreCase(filter.getType())
                .orAllOf(
                        qProduct.sizes.any().in(sizes),
                        qProduct.categories.any().in(categories),
                        qProduct.colors.any().in(colors)
                )
                .and(qProduct.isAvailable.eq(filter.isAvailable()))
                .and(qProduct.price.between(filter.getStartPrice(), filter.getEndPrice()));
        return (List<Product>) productRepository.findAll(predicate);
    }

    /**
     * This method retrieves the default list of sizes. If no sizes are specified in the filter,
     * it fetches all available sizes from the {@link ProductSizeRepository}.
     *
     * @param filter the {@link FilterProducts} object containing product filter criteria
     * @return a list of sizes, either from the filter or all available sizes if no sizes are provided in the filter
     */
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

    /**
     * This method retrieves the default list of categories. If no categories are specified in the filter or the
     * category list is empty, it fetches all available categories from the {@link ProductCategoryRepository}.
     *
     * @param filter the {@link FilterProducts} object containing product filter criteria
     * @return a list of categories, either from the filter or all available categories if none are provided or the list is empty
     */
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

    /**
     * This method retrieves the default list of colors. If no colors are specified in the filter,
     * it fetches all available colors from the {@link ProductColorRepository}.
     *
     * @param filter the {@link FilterProducts} object containing product filter criteria
     * @return a list of colors, either from the filter or all available colors if no colors are provided
     */
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
