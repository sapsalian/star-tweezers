package com.starforceps.starforceps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StarforcepsApplication {
	public static void main(String[] args) {
		SpringApplication.run(StarforcepsApplication.class, args);
	}

}
