package com.funkydeveloper.fashion_commerce.exception;

public record FashionExceptionResponse (
        int status,
        String error,
        String message,
        String path
) { }
