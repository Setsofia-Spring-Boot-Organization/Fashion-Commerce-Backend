package com.example.fashion_commerce.product.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


public class CreateNewProductRequest {
        private String name;
        private Double price;
        private String type;
        private List<String>sizes;
        private List<String> colors;
        private List<MultipartFile> images;
        private List<String> categories;
        private String description;

        public CreateNewProductRequest(String name, Double price, String type, List<String> sizes, List<String> colors, List<MultipartFile> images, List<String> categories, String description) {
                this.name = name;
                this.price = price;
                this.type = type;
                this.sizes = sizes;
                this.colors = colors;
                this.images = images;
                this.categories = categories;
                this.description = description;
        }

        public String getName() {
                return name;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public void setSizes(List<String> sizes) {
                this.sizes = sizes;
        }

        public void setColors(List<String> colors) {
                this.colors = colors;
        }

        public void setImages(List<MultipartFile> images) {
                this.images = images;
        }

        public void setCategories(List<String> categories) {
                this.categories = categories;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Double getPrice() {
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

        public List<MultipartFile> getImages() {
                return images;
        }

        public List<String> getCategories() {
                return categories;
        }

        public String getDescription() {
                return description;
        }

        @Override
        public String toString() {
                return "CreateNewProductRequest{" +
                        "name='" + name + '\'' +
                        ", price='" + price + '\'' +
                        ", type='" + type + '\'' +
                        ", sizes=" + sizes +
                        ", colors=" + colors +
                        ", images=" + images +
                        ", categories=" + categories +
                        ", description='" + description + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CreateNewProductRequest that = (CreateNewProductRequest) o;
                return Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(type, that.type) && Objects.equals(sizes, that.sizes) && Objects.equals(colors, that.colors) && Objects.equals(images, that.images) && Objects.equals(categories, that.categories) && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(name, price, type, sizes, colors, images, categories, description);
        }
}

