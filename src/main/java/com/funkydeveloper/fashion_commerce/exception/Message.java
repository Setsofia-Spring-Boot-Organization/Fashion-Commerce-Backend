package com.funkydeveloper.fashion_commerce.exception;

public enum Message {
    CANNOT_SAVE_THE_DATA("the submitted data cannot be saved"),
    THE_FOLLOWING_FIELDS_ARE_EMPTY("the following fields are empty: ");

    public final String label;
    Message(String label) {
        this.label = label;
    }
}
