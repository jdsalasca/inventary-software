package com.sena.inventarioback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
@EnableScheduling
@EnableCaching
@Validated
@EnableWebMvc
public class InventarioBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioBackApplication.class, args);
	}

}
