package com.example.fashion_commerce.product.requests;

import java.util.List;


public class FilterProducts {

    private String type;
    private List<String> sizes;
    private boolean isAvailable;
    private List<String> categories;
    private List<String> colors;
    private double startPrice;
    private double endPrice;

    public FilterProducts(List<String> sizes, boolean isAvailable, List<String> categories, List<String> colors) {
        this.sizes = sizes;
        this.isAvailable = isAvailable;
        this.categories = categories;
        this.colors = colors;
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

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }
}
