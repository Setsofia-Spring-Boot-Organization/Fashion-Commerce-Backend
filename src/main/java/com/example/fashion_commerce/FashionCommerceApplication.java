package com.example.fashion_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FashionCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionCommerceApplication.class, args);
	}

}
