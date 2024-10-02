package com.funkydeveloper.fashion_commerce.exception;

public enum Message {
    CANNOT_SAVE_THE_DATA("the submitted data cannot be saved"),
    THE_FOLLOWING_FIELDS_ARE_EMPTY("the following fields are empty: "),
    THE_REQUESTED_PRODUCT_ID_IS_INCORRECT("the requested product's id is incorrect"),
    THE_REQUESTED_GENDER_IS_INVALID("the requested gender cannot be found"),
    THE_PRODUCT_TYPES_COULD_NOT_BE_VERIFIED("could not verify the submitted product types");

    public final String label;
    Message(String label) {
        this.label = label;
    }
}
