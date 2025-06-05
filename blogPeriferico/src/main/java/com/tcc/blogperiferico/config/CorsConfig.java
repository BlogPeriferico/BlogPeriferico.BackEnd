package com.tcc.blogperiferico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Libera todas as rotas (/**)
                registry.addMapping("/**")
                        // Permite chamadas de qualquer origem
                        .allowedOrigins("*")
                        // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                        .allowedMethods("*");
            }
        };
    }
}
