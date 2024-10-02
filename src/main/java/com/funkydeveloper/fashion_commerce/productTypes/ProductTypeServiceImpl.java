package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.responses.CreatedProductTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ResponseEntity<Response<CreatedProductTypes>> createNewProduct(List<String> names) {

        List<ProductType> productTypes = new ArrayList<>();

        for (String name : names) {
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
