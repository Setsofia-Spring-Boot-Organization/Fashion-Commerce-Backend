package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.exception.Error;
import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.exception.Message;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.requests.CreateProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType createProductType) {

        List<ProductType> productTypes = new ArrayList<>();

        // verify the product type names
        List<String> validNames = getValidNames(createProductType);

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

    private List<String> getValidNames(CreateProductType createProductType) {

        List<String> validNames = new ArrayList<>();
        List<ProductType> productTypes = productTypeRepository.findAll();


        if (productTypes.isEmpty()) {
             validNames.addAll(createProductType.names());
             return validNames;
        }

        if (productTypes.size() <= createProductType.names().size()) {
            for (String name : createProductType.names()) {
                productTypes.forEach(
                        type -> {
                            if (!Objects.equals(name, type.getName())) {
                                validNames.add(name);
                            }
                        }
                );
                return validNames;
            }

        } else {
            for (ProductType productType : productTypes) {
                createProductType.names().forEach(
                        name -> {
                            if (!Objects.equals(name, productType.getName())) {
                                validNames.add(name);
                            }
                        }
                );
            }
            return validNames;
        }

        throw new FashionCommerceException(Error.INVALID_PRODUCT_TYPES, new Throwable(Message.THE_PRODUCT_TYPES_COULD_NOT_BE_VERIFIED.label));
    }
}
