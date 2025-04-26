package com.michele.caniglia.Esame.Java.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_KEY = "BearerAuth";


    // Permette di potersi autenticare su Swagger inserendo il JWT
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Esame Fine Corso Java")
                        .version("1.0")
                        .description("Documentazione API per il progetto finale"))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(BEARER_KEY,
                                new SecurityScheme()
                                        .name(BEARER_KEY)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}