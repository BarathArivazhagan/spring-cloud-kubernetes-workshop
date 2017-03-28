package com.barath.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
	
	@GetMapping("/")
	public String welcome(){
		return "WELCOME TO RESOURCE APPLICATION ";
	}

	@GetMapping("/resource")
	public String resource(){
		return "WELCOME TO RESOURCE APPLICATION ";
	}
}
