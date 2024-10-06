package com.example.fashion_commerce.product.productColor;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@QueryEntity
@Document(collection = "product_color")
public class ProductColor {

    @MongoId
    private String id;
    private String color;

    public ProductColor(String id, String color) {
        this.id = id;
        this.color = color;
    }

    public ProductColor() {}

    public ProductColor(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductColor that = (ProductColor) o;
        return Objects.equals(id, that.id) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }

    @Override
    public String toString() {
        return "ProductColor{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
