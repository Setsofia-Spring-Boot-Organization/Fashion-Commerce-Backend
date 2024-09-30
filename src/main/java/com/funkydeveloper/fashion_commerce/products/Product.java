package com.funkydeveloper.fashion_commerce.products;

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
@Document(collection = "product")
public class Product {

    @MongoId
    private String id;
    private String name;
    private String price;
    private List<String> sizes;
    private List<String> colors;
    private List<String> images;
    private List<String> genders;
    private boolean isAvailable;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
