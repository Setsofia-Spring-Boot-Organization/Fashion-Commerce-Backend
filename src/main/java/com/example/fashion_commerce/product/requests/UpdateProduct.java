package com.example.fashion_commerce.product.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProduct {
        private String name;
        private Double price;
        private List<String> types;
        private List<String>sizes;
        private List<String> colors;
        private List<MultipartFile> images;
        private List<String> imageUrls;
        private List<String> categories;
        private String description;
        private boolean available;

        public UpdateProduct(String name, Double price, List<String> types, List<String> sizes, List<String> colors, List<MultipartFile> images, List<String> imageUrls, List<String> categories, String description, boolean available) {
                this.name = name;
                this.price = price;
                this.types = types;
                this.sizes = sizes;
                this.colors = colors;
                this.images = images;
                this.imageUrls = imageUrls;
                this.categories = categories;
                this.description = description;
                this.available = available;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public List<String> getTypes() {
                return types;
        }

        public void setTypes(List<String> types) {
                this.types = types;
        }

        public List<String> getSizes() {
                return sizes;
        }

        public void setSizes(List<String> sizes) {
                this.sizes = sizes;
        }

        public List<String> getColors() {
                return colors;
        }

        public void setColors(List<String> colors) {
                this.colors = colors;
        }

        public List<MultipartFile> getImages() {
                return images;
        }

        public void setImages(List<MultipartFile> images) {
                this.images = images;
        }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getCategories() {
                return categories;
        }

    public void setCategories(List<String> categories) {
            this.categories = categories;
    }

    public String getDescription() {
            return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public boolean isAvailable() {
            return available;
    }

    public void setAvailable(boolean available) {
            this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateProduct that = (UpdateProduct) o;
        return available == that.available && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(types, that.types) && Objects.equals(sizes, that.sizes) && Objects.equals(colors, that.colors) && Objects.equals(images, that.images) && Objects.equals(imageUrls, that.imageUrls) && Objects.equals(categories, that.categories) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, types, sizes, colors, images, imageUrls, categories, description, available);
    }

    @Override
    public String toString() {
        return "UpdateProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", types=" + types +
                ", sizes=" + sizes +
                ", colors=" + colors +
                ", images=" + images +
                ", imageUrls=" + imageUrls +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}

