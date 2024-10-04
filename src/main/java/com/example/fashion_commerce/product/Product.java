package com.example.fashion_commerce.product;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@QueryEntity //this annotation helps to create a QueryDSL support for the entity
@Document(collection = "product")
public class Product {

    @MongoId
    private String id;
    private String name;
    private String price;
    private String type;
    private List<String> sizes;
    private List<String> colors;
    private List<String> images;
    private List<String> categories;
    private boolean isAvailable;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
