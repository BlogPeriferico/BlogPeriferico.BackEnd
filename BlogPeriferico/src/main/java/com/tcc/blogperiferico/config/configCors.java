package com.tcc.blogperiferico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class configCors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // CORRETO: usado com allowCredentials
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true); // Mantido true se for usar sessão/token via cookie
    }
}
