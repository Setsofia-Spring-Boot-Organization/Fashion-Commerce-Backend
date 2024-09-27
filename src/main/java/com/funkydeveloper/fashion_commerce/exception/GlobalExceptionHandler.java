package com.funkydeveloper.fashion_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FashionCommerceException.class)
    public ResponseEntity<FashionExceptionResponse> handleException(
            FashionCommerceException exception,
            WebRequest webRequest
    ) {
        HttpStatus status = HttpStatus.OK; // set the default status
        String path = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
        Error error = exception.error;

        switch (error) {
            case ERROR_SAVING_DATA -> status = HttpStatus.BAD_GATEWAY;
        }

        FashionExceptionResponse response = new FashionExceptionResponse(
                status.value(),
                error.label,
                exception.getMessage(),
                path
        );

        return new ResponseEntity<>(response, status);
    }
}
