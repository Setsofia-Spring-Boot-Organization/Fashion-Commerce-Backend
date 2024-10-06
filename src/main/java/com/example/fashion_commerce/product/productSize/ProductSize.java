package com.example.fashion_commerce.product.productSize;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@QueryEntity
@Document(collection = "product_size")
public class ProductSize {

    @MongoId
    private String id;
    private String size;

    public ProductSize(String size) {
        this.size = size;
    }

    public ProductSize() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSize that = (ProductSize) o;
        return Objects.equals(id, that.id) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "id='" + id + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
