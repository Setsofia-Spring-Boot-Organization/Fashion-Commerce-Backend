package com.example.fashion_commerce.product.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


public class CreateNewProductRequest {
        private String name;
        private Double price;
        private List<String> types;
        private List<String>sizes;
        private List<String> colors;
        private List<MultipartFile> images;
        private List<String> categories;
        private String description;

        public CreateNewProductRequest(String name, Double price, List<String> types, List<String> sizes, List<String> colors, List<MultipartFile> images, List<String> categories, String description) {
                this.name = name;
                this.price = price;
                this.types = types;
                this.sizes = sizes;
                this.colors = colors;
                this.images = images;
                this.categories = categories;
                this.description = description;
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

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CreateNewProductRequest request = (CreateNewProductRequest) o;
                return Objects.equals(name, request.name) && Objects.equals(price, request.price) && Objects.equals(types, request.types) && Objects.equals(sizes, request.sizes) && Objects.equals(colors, request.colors) && Objects.equals(images, request.images) && Objects.equals(categories, request.categories) && Objects.equals(description, request.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(name, price, types, sizes, colors, images, categories, description);
        }

        @Override
        public String toString() {
                return "CreateNewProductRequest{" +
                        "name='" + name + '\'' +
                        ", price=" + price +
                        ", types=" + types +
                        ", sizes=" + sizes +
                        ", colors=" + colors +
                        ", images=" + images +
                        ", categories=" + categories +
                        ", description='" + description + '\'' +
                        '}';
        }
}

