package com.thevault.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI vaultOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("The Vault API")
                        .version("1.0.0")
                        .description("The Vault - Premium Fashion Lifestyle Platform")
                        .contact(new Contact()
                                .name("The Vault Team")
                                .email("dev@thevault.com"))
                        .license(new License()
                                .name("MIT")));
    }
}
