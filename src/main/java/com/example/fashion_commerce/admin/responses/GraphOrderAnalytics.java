package com.example.fashion_commerce.admin.responses;

import java.util.List;

public record GraphOrderAnalytics (
    List<String> labels,
    List<Double> data
){}
