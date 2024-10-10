package com.example.fashion_commerce.order.requests;

import java.util.Objects;

public class OrderStatus {
    private OrderStatus status;

    public OrderStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status=" + status +
                '}';
    }
}
