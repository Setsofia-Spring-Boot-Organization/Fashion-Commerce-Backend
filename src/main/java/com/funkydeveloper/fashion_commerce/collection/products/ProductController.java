package com.funkydeveloper.fashion_commerce.collection.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public record ProductController(ProductService productService) {


}
