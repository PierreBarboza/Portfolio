package com.example.aulaOpenFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AulaOpenFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulaOpenFeignApplication.class, args);
	}

}
