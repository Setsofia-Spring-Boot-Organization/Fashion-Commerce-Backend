package com.funkydeveloper.fashion_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class FashionCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionCommerceApplication.class, args);
	}

}
