package com.compasso.parking_management_spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Parking Management")
                                .description("Parking Management REST API, structured using MVC, in addition to Spring Boot, Maven, JPA and MySQL.")
                                .version("v1")
                                .contact(new Contact()
                                        .name("Gilmar Soares, Lincoln Roberto, Matheus da Silva, Litman Marins")
                                        .email("litman.braga.pb@compasso.com.br")
                                )
                );
    }
}
