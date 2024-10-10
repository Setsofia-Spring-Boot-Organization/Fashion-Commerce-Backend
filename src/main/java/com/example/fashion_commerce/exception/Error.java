package com.example.fashion_commerce.exception;

public enum Error {
    ERROR_SAVING_DATA("error saving data"),
    NO_EMPTY_FIELDS_ALLOWED("no empty fields allowed"),
    NO_PRODUCT_FOUND("product unavailable"),
    INVALID_GENDER("invalid gender"),
    INVALID_PRODUCT_TYPES("invalid product type(s)"),
    FILE_SIZE_TOO_LARGE("file size too large"),
    INVALID_PRODUCT_IDS("invalid product id(s)"),
    INVALID_ORDER_STATUS("invalid order status");

    public final String label;
    Error(String label) {
        this.label = label;
    }
}
