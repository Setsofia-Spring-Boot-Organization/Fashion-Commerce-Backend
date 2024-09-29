package com.funkydeveloper.fashion_commerce.exception;

public enum Error {
    ERROR_SAVING_DATA("error saving data"),
    NO_EMPTY_FIELDS_ALLOWED("no empty fields allowed");

    public final String label;
    Error(String label) {
        this.label = label;
    }
}
