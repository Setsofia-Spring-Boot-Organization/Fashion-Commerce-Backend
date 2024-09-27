package com.funkydeveloper.fashion_commerce.generics;

public class Response<T> {
    private int status;
    private String message;
    private T data;
}
