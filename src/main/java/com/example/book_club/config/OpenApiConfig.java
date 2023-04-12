package com.example.book_club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
            .title("Book Club App API")
            .description("An API for an imaginary application which would allow users for form and join book circles to read together.")
            .version("v0.0.0.0.0.0.0.0.never happening"));
    }
}