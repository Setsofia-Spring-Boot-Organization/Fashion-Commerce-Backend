package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.requests.CreateProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType createProductType) {

        List<ProductType> productTypes = new ArrayList<>();
        List<String> validNames = new ArrayList<>();
        List<ProductType> productTypes1 = productTypeRepository.findAll();

        for (ProductType productType : productTypes1) {
            createProductType.names().forEach(
                    name -> {
                        if (!Objects.equals(name, productType.getName())) {
                            validNames.add(name);
                        }
                    }
            );
        }

        for (String name : validNames) {
            productTypes.add(
                    ProductType.builder()
                            .name(name)
                            .build()
            );
        }

        List<ProductType> createdProductTypes = productTypeRepository.saveAll(productTypes);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<List<ProductType>>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product type(s) created successfully")
                        .data(createdProductTypes)
                        .build()
        );
    }
}
