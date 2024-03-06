package com.edmart.vendorservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.client.RestTemplate;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class VendorConfig {

    @Value("${spring-boot.version}")
    private static String version;

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edmart.vendorservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); // Refactored for better organization
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Vendor Service API Documentation") // Concise title
                .description("REST API endpoints for Vendor Service interactions.") // Clearer description
                .version("1.0.0") // Replace with actual version
                .contact(new Contact("Nestor Martourez", "vendor-service.edmart.com", "mail.astraopencollab@gmail.com"))
                .license("Apache License, Version 2.0")
                .licenseUrl("https://opensource.org/licenses/apache-2.0/")
                .build();
    }
    @Bean
    public NewTopic vendorKafkaTopic(){
        return TopicBuilder.name("vendor-topic")
                .build();
    }
}
