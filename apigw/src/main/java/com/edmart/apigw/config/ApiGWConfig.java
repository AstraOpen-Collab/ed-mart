package com.edmart.apigw.config;



import com.google.common.base.Predicate;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;

@Configuration
public class ApiGWConfig{


    @Value("${spring.application.name}")
    private String appName;


    @Bean
    public Docket productApi() {
        Contact contact =new Contact(
                "Vineet Mishra",
                "https://github.com/xbox2204",
                "whatwillyoudo@withmyemail.com"
        );
        ApiInfo apiInfo= new ApiInfoBuilder().title("VINEET SPRING-BOOT API")
                .description("Spring-Boot for all")
                .termsOfServiceUrl("jUST CHILL!!!")
                .contact(contact)
                .licenseUrl("something@something.com").version("1.0").build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edmart.vendorservice.controller"))
                .apis(RequestHandlerSelectors.basePackage("com.edmart.category.controller"))
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    @Bean
    public CommandLineRunner openApiGroups(
            RouteDefinitionLocator locator,
            SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> Objects.requireNonNull(locator
                        .getRouteDefinitions().collectList().block())
                .stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-service"))
                .map(id -> id.replace("-service", ""))
                .forEach(swaggerUiParameters::addGroup);
    }

}
