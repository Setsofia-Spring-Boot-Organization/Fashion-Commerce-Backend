package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.requests.CreateProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType createProductType) {

        List<ProductType> productTypes = new ArrayList<>();

        // verify the product type names
        Set<String> validNames = getValidNames(createProductType);

        for (String name : validNames) {
            productTypes.add(
                    ProductType.builder()
                            .name(name)
                            .build()
            );
        }

        List<ProductType> createdProductTypes = productTypeRepository.saveAll(productTypes);

        log.info("created product types {}", createdProductTypes);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<List<ProductType>>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product type(s) created successfully")
                        .data(createdProductTypes)
                        .build()
        );
    }

    private Set<String> getValidNames(CreateProductType createProductType) {

        Set<String> validNames = new HashSet<>();
        List<ProductType> productTypes = productTypeRepository.findAll();

        if (productTypes.isEmpty()) {
            validNames.addAll(createProductType.names());
            return validNames;
        }

        List<String> existingNames = new ArrayList<>();
        for (ProductType type : productTypes) {
            existingNames.add(type.getName());
        }

        for (String name : createProductType.names()) {
            for (String ignored : existingNames) {
                if (!existingNames.contains(name)) {
                    validNames.add(name);
                }
            }
        }

        return validNames;
    }
}
