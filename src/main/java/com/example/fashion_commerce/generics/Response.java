package com.example.fashion_commerce.generics;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int status;
    private String message;
    private T data;
    private String total;

    public Response(int status, String message, T data, String total) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response() {}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return status == response.status && Objects.equals(message, response.message) && Objects.equals(data, response.data) && Objects.equals(total, response.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, data, total);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", total='" + total + '\'' +
                '}';
    }
}
