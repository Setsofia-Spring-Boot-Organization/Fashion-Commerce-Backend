package com.funkydeveloper.fashion_commerce.products;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @MongoId
    private String id;
    private String name;
    private String price;
    private List<String> size;
    private List<String> color;
    private List<String> images;
    private boolean isAvailable;

    private String description;
}
