package com.example.fashion_commerce.product.productSize;

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
@Document(collection = "product_size")
public class ProductSize {

    @MongoId
    private String id;
    private String size;
}
