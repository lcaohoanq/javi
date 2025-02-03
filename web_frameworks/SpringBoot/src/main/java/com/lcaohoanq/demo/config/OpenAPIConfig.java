package com.lcaohoanq.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                      .title("My API Services")
                      .version("1.0.0")
                      .description("REST API documentation for ... services")
                      .contact(new Contact()
                                   .name("Your Team Name")
                                   .email("team@example.com"))
                      .license(new License()
                                   .name("Private License")))
            .externalDocs(new ExternalDocumentation()
                              .description("API Documentation")
                              .url("https://your-docs-url.com"))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                            .addSecuritySchemes("bearerAuth",
                                                new SecurityScheme()
                                                    .name("bearerAuth")
                                                    .type(SecurityScheme.Type.HTTP)
                                                    .scheme("bearer")
                                                    .bearerFormat("JWT")
                                                    .description("Please enter JWT token"))
                            .addSchemas("ApiResponse", createApiResponseSchema())
                            .addSchemas("ApiErrorResponse", createApiErrorSchema()))
            .addServersItem(new Server().url("/").description("Local server"));
    }

    private Schema<?> createApiResponseSchema() {
        Schema<?> apiResponseSchema = new Schema<>()
            .type("object")
            .addProperty("message", new Schema<>().type("string"))
            .addProperty("reason", new Schema<>().type("string"))
            .addProperty("statusCode", new Schema<>().type("integer"))
            .addProperty("isSuccess", new Schema<>().type("boolean"))
            .addProperty("data", new Schema<>().type("object"));
        return apiResponseSchema;
    }

    private Schema<?> createApiErrorSchema() {
        Schema<?> apiErrorSchema = new Schema<>()
            .type("object")
            .addProperty("message", new Schema<>().type("string").example("Error message"))
            .addProperty("reason", new Schema<>().type("string").example("Detailed error reason"))
            .addProperty("status_code", new Schema<>().type("integer").example(400))
            .addProperty("is_success", new Schema<>().type("boolean").example(false));
        return apiErrorSchema;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/**")  // Match all paths
            .build();
    }
}