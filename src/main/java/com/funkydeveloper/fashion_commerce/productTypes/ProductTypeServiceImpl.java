package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.responses.CreatedProductTypes;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Response<CreatedProductTypes>> createNewProduct(List<String> names) {

        List<ProductType> productTypes = new ArrayList<>();
        List<String> validNames = new ArrayList<>();
        List<ProductType> productTypes1 = productTypeRepository.findAll();

        for (ProductType productType : productTypes1) {
            if (!Objects.equals(names.iterator().next(), productType.getName())) {
                validNames.add(names.iterator().next());
            }
        }

        for (String name : validNames) {
            productTypes.add(
                    ProductType.builder()
                            .name(name)
                            .build()
            );
        }

        productTypeRepository.saveAll(productTypes);

        return null;
    }
}
