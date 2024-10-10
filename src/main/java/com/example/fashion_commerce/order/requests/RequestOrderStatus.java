package com.example.fashion_commerce.order.requests;

import com.example.fashion_commerce.order.OrderStatus;

import java.util.Objects;

public class RequestOrderStatus {

    private String status;

    public RequestOrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestOrderStatus that = (RequestOrderStatus) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return "RequestOrderStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
