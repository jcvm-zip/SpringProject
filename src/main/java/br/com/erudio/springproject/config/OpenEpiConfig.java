package br.com.erudio.springproject.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;


@Configuration
public class OpenEpiConfig {

    @Bean
    public OpenAPI customOpenAPIOpenEpiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 19 and Spring Boot")
                        .version("v1")
                        .description("")
                        .termsOfService("")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("")
                        )
                );
    }

}
