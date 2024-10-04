package com.example.fashion_commerce.product.productColor;

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
@Document(collection = "product_color")
public class ProductColor {

    @MongoId
    private String id;
    private String color;
}
