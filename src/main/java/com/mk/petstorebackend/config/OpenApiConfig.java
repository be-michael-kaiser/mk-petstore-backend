package com.mk.petstorebackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI petstoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("mk-petstore API")
                        .description("OpenAPI contract for the React frontend and Spring Boot backend migration")
                        .version("1.0.0"));
    }
}
