package com.funkydeveloper.fashion_commerce.product.productSize;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.productSize.requests.CreateProductSize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;



    @Override
    public ResponseEntity<Response<List<ProductSize>>> createNewProductSize(CreateProductSize productSize) {

        List<ProductSize> productSizes = new ArrayList<>();

        // verify the product type names
        Set<String> validSizes = getValidSizes(productSize);

        for (String size : validSizes) {
            productSizes.add(
                    ProductSize.builder()
                            .size(size)
                            .build()
            );
        }

        List<ProductSize> createdProductTypes = productSizeRepository.saveAll(productSizes);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<List<ProductSize>>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product sizes(s) created successfully")
                        .data(createdProductTypes)
                        .build()
        );
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

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<ProductSize>>builder()
                        .status(HttpStatus.OK.value())
                        .message("product types")
                        .data(productSizes)
                        .build()
        );
    }
}
