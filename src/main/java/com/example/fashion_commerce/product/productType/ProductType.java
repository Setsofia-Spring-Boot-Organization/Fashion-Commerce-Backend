package com.example.fashion_commerce.product.productType;

import com.querydsl.core.annotations.QueryEntity;
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
@Document(collection = "product_type")
public class ProductType {

    @MongoId
    private String id;
    private String name;
}
