package com.funkydeveloper.fashion_commerce.productTypes;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.productTypes.responses.CreatedProductTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product-type")
@RequiredArgsConstructor
public class ProductTypeController {

    @PostMapping
    public ResponseEntity<Response<CreatedProductTypes>> createNewProductType(
            @RequestBody List<String> names
    ) {
        return
    }
}
