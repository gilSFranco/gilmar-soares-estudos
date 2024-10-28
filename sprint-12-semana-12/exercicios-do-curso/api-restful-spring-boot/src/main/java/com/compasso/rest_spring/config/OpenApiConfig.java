package com.compasso.rest_spring.config;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 21 and Spring Boot 3")
                        .version("v1.0.0")
                        .description("CompassUOL course - Learning RESTful API")
                        .termsOfService("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .license(
                                new License()
                                            .name("Apache 2.0")
                                            .url("http://www.apache.org/licenses/LICENSE-2.0.html"
                                        )
                        )

                );
    }
}
