package com.funkydeveloper.fashion_commerce.productTypes;

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
@Document(collection = "product_types")
public class ProductType {

    @MongoId
    private String id;
    private String name;
}
