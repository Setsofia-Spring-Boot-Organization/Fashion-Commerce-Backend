package com.example.fashion_commerce.product.productCategory;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;


@QueryEntity
@Document(collection = "category")
public class ProductCategory {

    @MongoId
    private String id;
    private String category;

    public ProductCategory(String category) {
        this.category = category;
    }

    public ProductCategory() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }
}
