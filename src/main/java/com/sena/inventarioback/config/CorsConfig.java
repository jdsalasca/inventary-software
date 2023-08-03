package com.sena.inventarioback.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CorsConfig {

    @Bean
    CorsFilter corsFilter() {
        log.info("Configuration CORS");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // Allow all origins
        config.addAllowedOrigin("*");

        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT", "PATCH"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}