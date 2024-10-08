package com.example.fashion_commerce.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Value("${deployed-frontend-url}")
    private String DEPLOYED_FRONTEND_URL;
    @Value("${local-frontend-url}")
    private String LOCAL_FRONTEND_URL;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/v1/**")
                .allowedOrigins(LOCAL_FRONTEND_URL+"/", DEPLOYED_FRONTEND_URL+"/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH");
    }
}
