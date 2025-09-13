package com.lcaohoanq.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "FPT BE Service API",
        version = "1.0",
        contact = @Contact(
            name = "API Support",
            email = "support@FPT BE.com"
        )
    ),
    security = @SecurityRequirement(name = "bearer-jwt"),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local Dev (HTTP)"),
    }
)
@SecurityScheme(
    name = "bearer-jwt",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    bearerFormat = "JWT"
)
public class OpenAPIConfig {

}