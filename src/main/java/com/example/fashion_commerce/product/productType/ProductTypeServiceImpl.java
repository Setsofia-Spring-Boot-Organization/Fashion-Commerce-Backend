package com.example.fashion_commerce.product.productType;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.productType.requests.CreateProductType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ResponseEntity<Response<List<ProductType>>> createNewProduct(CreateProductType createProductType) {

        List<ProductType> productTypes = new ArrayList<>();

        // verify the product type names
        Set<String> validNames = getValidNames(createProductType);

        for (String name : validNames) {
            productTypes.add(
                    new ProductType(name)
            );
        }

        List<ProductType> createdProductTypes = productTypeRepository.saveAll(productTypes);

        Response<List<ProductType>> productTypeResponse = new Response<>(
                HttpStatus.CREATED.value(),
                "product type(s) created successfully",
                createdProductTypes
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(productTypeResponse);
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



    @Override
    public ResponseEntity<Response<List<ProductType>>> getProductTypes() {
        List<ProductType> productTypes = productTypeRepository.findAll();

        Response<List<ProductType>> productTypesResponse = new Response<>(
                HttpStatus.OK.value(),
                "product types",
                productTypes
        );

        return ResponseEntity.status(HttpStatus.OK).body(productTypesResponse);
    }


    @Override
    public void saveTypes(CreateProductType types) {
        List<ProductType> productType = new ArrayList<>();

        // verify the product type names
        List<String> validTypes = productTypeRepository.findAll().stream().map(ProductType::getName).toList();
        types.names().removeAll(validTypes);

        for (String name : types.names()) {
            productType.add(
                    new ProductType(name)
            );
        }

        productTypeRepository.saveAll(productType);
    }
}
