package com.example.fashion_commerce.generics;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int status;
    private String message;
    private T data;
    private String total;
}
