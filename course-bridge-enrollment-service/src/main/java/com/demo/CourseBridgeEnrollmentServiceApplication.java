package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CourseBridgeEnrollmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseBridgeEnrollmentServiceApplication.class, args);
	}

}
