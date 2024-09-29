package com.funkydeveloper.fashion_commerce.generics;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Response<T> {
    private int status;
    private String message;
    private T data;
}
