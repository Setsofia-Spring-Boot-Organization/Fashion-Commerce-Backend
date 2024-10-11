package com.example.fashion_commerce.exception;

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
            case NO_EMPTY_FIELDS_ALLOWED,
                 INVALID_GENDER,
                 INVALID_PRODUCT_TYPES,
                 FILE_SIZE_TOO_LARGE,
                 INVALID_PRODUCT_IDS,
                 INVALID_ORDER_STATUS,
                 INVALID_ORDER_ID -> status = HttpStatus.BAD_REQUEST;
            case ERROR_CREATING_ORDER -> status = HttpStatus.CONFLICT;
            case NO_PRODUCT_FOUND -> status = HttpStatus.NOT_FOUND;
        }

        FashionExceptionResponse response = new FashionExceptionResponse(
                status.value(),
                error.label,
                exception.getCause().getMessage(),
                path
        );

        return new ResponseEntity<>(response, status);
    }
}
