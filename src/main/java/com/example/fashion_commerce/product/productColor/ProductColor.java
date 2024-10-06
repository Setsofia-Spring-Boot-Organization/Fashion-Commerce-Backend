package com.example.fashion_commerce.product.productColor;

import com.mysema.query.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@QueryEntity
@Document(collection = "product_color")
public class ProductColor {

    @MongoId
    private String id;
    private String color;

    public ProductColor(String color) {
        this.color = color;
    }

    public ProductColor() {}

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
