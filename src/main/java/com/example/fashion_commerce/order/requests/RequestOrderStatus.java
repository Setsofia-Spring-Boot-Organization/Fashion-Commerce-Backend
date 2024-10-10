package com.example.fashion_commerce.order.requests;

import com.example.fashion_commerce.order.OrderStatus;

import java.util.Objects;

public class RequestOrderStatus {

    private OrderStatus status;

    public RequestOrderStatus(OrderStatus status) {
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
        RequestOrderStatus that = (RequestOrderStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return "RequestOrderStatus{" +
                "status=" + status +
                '}';
    }
}
