package com.funkydeveloper.fashion_commerce.exception;

public enum Message {
    CANNOT_SAVE_THE_DATA("the submitted data cannot be saved");

    public final String label;
    Message(String label) {
        this.label = label;
    }
}
