package com.funkydeveloper.fashion_commerce.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FashionCommerceException extends RuntimeException {
    final Error error;

    public FashionCommerceException(Error error) {
        super(error.label);
        this.error = error;
    }

    public FashionCommerceException(Error error, Throwable cause) {
        super(error.label, cause);
        this.error = error;
    }
}
