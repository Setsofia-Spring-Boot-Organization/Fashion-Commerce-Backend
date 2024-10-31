package com.example.fashion_commerce.product.productColor;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productColor.requests.CreateProductColor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductColorServiceImpl implements ProductColorService {


    private final ProductColorRepository productColorRepository;

    public ProductColorServiceImpl(ProductColorRepository productColorRepository) {
        this.productColorRepository = productColorRepository;
    }

    @Override
    public ResponseEntity<Response<List<ProductColor>>> createNewProductColor(CreateProductColor productColor) {

        List<ProductColor> colors = new ArrayList<>();

        // verify the product type names
        List<String> validColors = getValidColors(productColor);

        for (String color : validColors) {
            colors.add(
                    new ProductColor(color)
            );
        }

        List<ProductColor> productColors = productColorRepository.saveAll(colors);

        Response<List<ProductColor>> productColorResponse = new Response<>(
                HttpStatus.CREATED.value(),
                "product color(s) created successfully",
                productColors
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(productColorResponse);
    }

    private List<String> getValidColors(CreateProductColor productColor) {

        List<String> colors = productColorRepository.findAll().stream().map(ProductColor::getColor).toList();
        productColor.colors().removeAll(colors);

        return productColor.colors();
    }


    @Override
    public ResponseEntity<Response<List<ProductColor>>> getProductColors() {
        List<ProductColor> colors = productColorRepository.findAll();

        System.out.println("colors = " + colors);

        Response<List<ProductColor>> productColorResponse = new Response<>(
                HttpStatus.OK.value(),
                "products colors",
                colors
        );

        return ResponseEntity.status(HttpStatus.OK).body(productColorResponse);
    }


    @Override
    public void saveColors(CreateProductColor colors) {
        List<ProductColor> productColors = new ArrayList<>();

        // verify the product type names
        List<String> validColors = productColorRepository.findAll().stream().map(ProductColor::getColor).toList();
        colors.colors().removeAll(validColors);

        for (String color : colors.colors()) {
            productColors.add(
                    new ProductColor(color.toUpperCase())
            );
        }

        productColorRepository.saveAll(productColors);
    }
}
