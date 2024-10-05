package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.ProductPredicates;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductColorServiceImpl implements ProductColorService {


    private final ProductColorRepository productColorRepository;
    private final ProductPredicates productPredicates;

    public ProductColorServiceImpl(ProductColorRepository productColorRepository, ProductPredicates productPredicates) {
        this.productColorRepository = productColorRepository;
        this.productPredicates = productPredicates;
    }

    @Override
    public ResponseEntity<Response<List<ProductColor>>> createNewProductColor(CreateProductColor productColor) {

        List<ProductColor> colors = new ArrayList<>();

        // verify the product type names
        Set<String> validColors = getValidColors(productColor);

        for (String color : validColors) {
            colors.add(
                    ProductColor.builder()
                            .color(color)
                            .build()
            );
        }

        List<ProductColor> productColors = productColorRepository.saveAll(colors);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<List<ProductColor>>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product sizes(s) created successfully")
                        .data(productColors)
                        .build()
        );
    }

    private Set<String> getValidColors(CreateProductColor productColor) {

        Set<String> validColors = new HashSet<>();
        List<ProductColor> colors = productColorRepository.findAll();

        if (colors.isEmpty()) {
            validColors.addAll(productColor.colors());
            return validColors;
        }

        List<String> existingColors = new ArrayList<>();
        for (ProductColor color : colors) {
            existingColors.add(color.getColor());
        }

        for (String color : productColor.colors()) {
            for (String ignored : existingColors) {
                if (!existingColors.contains(color)) {
                    validColors.add(color);
                }
            }
        }

        return validColors;
    }


    @Override
    public ResponseEntity<Response<List<ProductColor>>> getProductColors() {

        var products = productPredicates.globalProductFilter(
                "Dior",
                List.of("Ss", "L"),
                false,
                List.of("Mens"),
                List.of("Blacks"),
                "15s",
                "1500s"
        );
        log.info("the predicate function {}", products);

        List<ProductColor> colors = productColorRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<ProductColor>>builder()
                        .status(HttpStatus.OK.value())
                        .message("product colors")
                        .data(colors)
                        .build()
        );
    }
}
