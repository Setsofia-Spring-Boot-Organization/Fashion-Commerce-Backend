package com.example.fashion_commerce.configurations;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Scheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10000)
    public void request() {
        String url = "https://fashion-commerce.onrender.com";

        try {
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("response = " + response);
        } catch (RestClientException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
