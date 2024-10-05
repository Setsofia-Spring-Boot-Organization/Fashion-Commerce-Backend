package com.example.fashion_commerce.product.requests;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterProducts {

    private String type;
    private List<String> sizes;
    private boolean isAvailable;
    private List<String> categories;
    private List<String> colors;
    private String startPrice;
    private String endPrice;
}
