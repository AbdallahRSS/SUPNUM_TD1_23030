package com.supnum.supnum_td.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI SupnumOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Gestion des Serveurs")
                    .description("Service de monitoring des serveurs pour le data center")
                    .version("1.0"));

    }
}
