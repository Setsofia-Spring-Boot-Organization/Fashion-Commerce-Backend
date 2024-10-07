package com.example.fashion_commerce.product;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@QueryEntity //this annotation helps to create a QueryDSL support for the entity
@Document(collection = "product")
public class Product {

    @MongoId
    private String id;
    private String name;
    private String price;
    private String type;
    private List<String> sizes;
    private List<String> colors;
    private List<String> images;
    private List<String> categories;
    private boolean isAvailable;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String price, String type, List<String> sizes, List<String> colors, List<String> images, List<String> categories, boolean isAvailable, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.sizes = sizes;
        this.colors = colors;
        this.images = images;
        this.categories = categories;
        this.isAvailable = isAvailable;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public List<String> getColors() {
        return colors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getImages() {
        return images;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isAvailable == product.isAvailable && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(type, product.type) && Objects.equals(sizes, product.sizes) && Objects.equals(colors, product.colors) && Objects.equals(images, product.images) && Objects.equals(categories, product.categories) && Objects.equals(description, product.description) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, type, sizes, colors, images, categories, isAvailable, description, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id +
                ", name='" + name +
                ", price='" + price +
                ", type='" + type +
                ", sizes=" + sizes +
                ", colors=" + colors +
                ", images=" + images +
                ", categories=" + categories +
                ", isAvailable=" + isAvailable +
                ", description='" + description +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
