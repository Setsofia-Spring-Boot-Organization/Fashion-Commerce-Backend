package com.example.fashion_commerce.product.requests;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewProductRequest {
        private String name;
        private String price;
        private String type;
        private List<String>sizes;
        private List<String> colors;
        private List<MultipartFile> images;
        private List<String> categories;
        private String description;
}

