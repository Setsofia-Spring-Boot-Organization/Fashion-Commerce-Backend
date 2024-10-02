package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.responses.CreatedProductTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    @Override
    public ResponseEntity<Response<CreatedProductTypes>> createNewProduct(List<String> names) {
        return null;
    }
}
