package com.gdhardy.simplersvp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * ApiDocConfig
 */
@Configuration
public class ApiDocConfig {

  @Bean
  public OpenAPI configOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("Guest List Manager API")
              .description("Simple Spring Boot API for managing a guest list.")
              .version("v0.0.1"))
              .externalDocs(new ExternalDocumentation()
              .description("G. D. Hardy Design & Consulting")
              .url("https://gdhardy.com"));
  }
}