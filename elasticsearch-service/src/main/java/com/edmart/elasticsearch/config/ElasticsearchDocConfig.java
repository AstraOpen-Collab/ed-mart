package com.edmart.elasticsearch.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ElasticsearchDocConfig {

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
                .apis(RequestHandlerSelectors.any())
                .build();
    }
}
