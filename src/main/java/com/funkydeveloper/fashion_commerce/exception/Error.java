package com.funkydeveloper.fashion_commerce.exception;

public enum Error {
    ERROR_SAVING_DATA("error saving data");

    public final String label;
    Error(String label) {
        this.label = label;
    }
}
