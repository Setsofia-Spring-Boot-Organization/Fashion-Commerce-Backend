package com.example.fashion_commerce.product.requests;

import java.util.List;
import java.util.Objects;


public class FilterProducts {

    private String type;
    private List<String> sizes;
    private boolean isAvailable;
    private List<String> categories;
    private List<String> colors;
    private String startPrice;
    private String endPrice;

    public FilterProducts(List<String> sizes, boolean isAvailable, List<String> categories, List<String> colors) {
        this.sizes = sizes;
        this.isAvailable = isAvailable;
        this.categories = categories;
        this.colors = colors;
    }

    public FilterProducts() {}

    public boolean isAvailable() {
        return isAvailable;
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

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    @Override
    public String toString() {
        return "FilterProducts{" +
                "type='" + type + '\'' +
                ", sizes=" + sizes +
                ", isAvailable=" + isAvailable +
                ", categories=" + categories +
                ", colors=" + colors +
                ", startPrice='" + startPrice + '\'' +
                ", endPrice='" + endPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterProducts that = (FilterProducts) o;
        return isAvailable == that.isAvailable && Objects.equals(type, that.type) && Objects.equals(sizes, that.sizes) && Objects.equals(categories, that.categories) && Objects.equals(colors, that.colors) && Objects.equals(startPrice, that.startPrice) && Objects.equals(endPrice, that.endPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sizes, isAvailable, categories, colors, startPrice, endPrice);
    }
}
