package com.sena.inventarioback.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import jakarta.annotation.Priority;

@Configuration
@Priority(value = 1)
public class DataSourceConfig {
//
	private static final String URL = System.getenv("DATASOURCE_URL");
	private static final String USERNAME = System.getenv("DATASOURCE_USERNAME");
	private static final String PASSWORD = System.getenv("DATASOURCE_PASSWORD");


	//TODO use HIKARI insted to provide a more efficient connection
	@Bean
	DataSource dataSource() {
		var dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}

}