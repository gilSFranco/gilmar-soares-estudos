package com.compass.msuser.config;

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
                                .title("User Microservice")
                                .description("User microservice API, structured using MVC, together with Spring Boot, Maven, JPA and MySQL. Integrated with a messaging microservice using RabbitMQ. Dockerized and deployed on AWS.")
                                .version("v1")
                                .contact(new Contact()
                                        .name("Gilmar Soares Franco")
                                        .email("https://github.com/gilSFranco/")
                                )
                );
    }
}
