package com.example.fashion_commerce.product.productSize;


import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productSize.requests.CreateProductSize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;

    public ProductSizeServiceImpl(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }


    @Override
    public ResponseEntity<Response<List<ProductSize>>> createNewProductSize(CreateProductSize productSize) {

        List<ProductSize> productSizes = new ArrayList<>();

        // verify the product type names
        Set<String> validSizes = getValidSizes(productSize);

        for (String size : validSizes) {
            productSizes.add(
                    new ProductSize(size)
            );
        }

        List<ProductSize> createdProductSizes = productSizeRepository.saveAll(productSizes);

        Response<List<ProductSize>> productSizesResponse = new Response<>(
                HttpStatus.CREATED.value(),
                "product sizes(s) created successfully",
                createdProductSizes
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(productSizesResponse);
    }

    private Set<String> getValidSizes(CreateProductSize productSize) {

        Set<String> validSizes = new HashSet<>();
        List<ProductSize> productSizes = productSizeRepository.findAll();

        if (productSizes.isEmpty()) {
            validSizes.addAll(productSize.sizes());
            return validSizes;
        }

        List<String> existingSizes = new ArrayList<>();
        for (ProductSize size : productSizes) {
            existingSizes.add(size.getSize());
        }

        for (String name : productSize.sizes()) {
            for (String ignored : existingSizes) {
                if (!existingSizes.contains(name)) {
                    validSizes.add(name);
                }
            }
        }

        return validSizes;
    }



    @Override
    public ResponseEntity<Response<List<ProductSize>>> getAllProductSizes() {

        List<ProductSize> productSizes = productSizeRepository.findAll();

        Response<List<ProductSize>> productSizesResponse = new Response<>(
                HttpStatus.OK.value(),
                "product sizes",
                productSizes
        );

        return ResponseEntity.status(HttpStatus.OK).body(productSizesResponse);
    }



    @Override
    public void saveSizes(CreateProductSize sizes) {
        List<ProductSize> productSizes = new ArrayList<>();

        // verify the product type names
        List<String> validSizes = productSizeRepository.findAll().stream().map(ProductSize::getSize).toList();
        sizes.sizes().removeAll(validSizes);

        for (String size : sizes.sizes()) {
            productSizes.add(
                    new ProductSize(size.toUpperCase())
            );
        }

        productSizeRepository.saveAll(productSizes);
    }
}
