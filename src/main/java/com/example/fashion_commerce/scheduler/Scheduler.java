package com.example.fashion_commerce.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Scheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 500_000)
    public void request() {
        String url = "https://fashion-commerce.onrender.com/api/v1/schedule";

        try {
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("response = " + response);
        } catch (RestClientException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
