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
            case ERROR_SAVING_DATA,
                 NO_PRODUCT_FOUND,
                 INVALID_GENDER -> status = HttpStatus.BAD_GATEWAY;
            case NO_EMPTY_FIELDS_ALLOWED -> status = HttpStatus.BAD_REQUEST;
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
