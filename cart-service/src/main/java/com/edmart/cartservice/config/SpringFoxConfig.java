package com.edmart.cartservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("cart-service")
                .packagesToScan("com.edmart.cartservice.controller")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Cart Service")
                        .description("Cart API")
                        .contact(contact())
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    public Contact contact(){
        return new Contact()
                .name("Developers: Nestor Martourez")
                .url("cart-service.edmart.com")
                .email("mail.astraopencollab@gmail.com");
    }
}
