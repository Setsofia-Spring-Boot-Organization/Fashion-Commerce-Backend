package com.example.fashion_commerce.order.requests;

import java.util.Objects;

public class OrderProductsIds {
        String id;
        int quantity;

    public OrderProductsIds(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsIds that = (OrderProductsIds) o;
        return quantity == that.quantity && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "OrderProductsIds{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
