package com.anaparthi.path_tracker.auth;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("oauth2"))
                .components(new Components().addSecuritySchemes("oauth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows().password(
                                                        new OAuthFlow().tokenUrl("/api/auth/login")
                                                                .scopes(new Scopes())))));
    }
}