package com.sena.inventarioback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//               // .allowedOrigins("http://localhost:8080", "http://localhost:3000","http://localhost:12000")
//        .allowedOrigins("*")        
//        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization");
//                //.allowCredentials(true);
//    }
//    
//    
//    
//}