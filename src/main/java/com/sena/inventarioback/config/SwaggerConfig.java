package com.sena.inventarioback.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
/**
 * Swagger configuration for the Sena Inventory Project.
 *
 * <p>This class sets up the Swagger UI documentation for the Sena Inventory Project API. It uses
 * OpenAPI 3 specification to define the API documentation, which includes endpoint details, request
 * and response models, and other relevant information.
 *
 * <p>The Swagger documentation can be accessed by navigating to the Swagger UI endpoint at
 * /swagger-ui/index.html after running the application.
 *
 * <p>Author: Juan David Salas Camargo
 * <p>Email: jdsalasc@unal.edu.co
 *
 * @see io.swagger.v3.oas.models.OpenAPI
 * @see io.swagger.v3.oas.models.info.Info
 * @see io.swagger.v3.oas.models.info.Contact
 */
@Configuration
//@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {
	io.swagger.v3.oas.models.info.Info info = new io.swagger.v3.oas.models.info.Info();

	@Bean
	OpenAPI customOpenAPI() {
		info.setTitle("SENA- PROYECTO INVENTARIO DE SOFTWARE");
		info.setVersion("v0.0.1");
		info.setDescription("este es un proyecto para el sena, el proyecto inventario");
		info.setContact(new io.swagger.v3.oas.models.info.Contact().email("jdsalasc@unal.edu.co").name("Juan David Salas"));
		info.setLicense(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"));
		var server = new io.swagger.v3.oas.models.servers.Server();
		server.setUrl("http://localhost:8080/api/");
		server.setDescription("local enviroment");
		var serverDev = new io.swagger.v3.oas.models.servers.Server();
		serverDev.setUrl("https://inventario-3hbd.onrender.com/api/");
		serverDev.setDescription("dev enviroment");
		return new OpenAPI()
				.info(info)
				.servers(List.of(serverDev,server));
	}

}