package com.example.fashion_commerce.product.productCategory;

import com.mysema.query.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@QueryEntity
@Document(collection = "category")
public class ProductCategory {

    @MongoId
    private String id;
    private String category;
}
