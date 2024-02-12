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
    public Docket api() throws IOException, XmlPullParserException {
//        MavenXpp3Reader reader = new MavenXpp3Reader();
//        Model model = reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edmart.vendorservice.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("Documentation Name: Vendor Service", "This API Documentation provides description to the various endpoints that makes up the Vendor-Service. It also provides description and ways to use the onsite web tool provided by swagger2 to test all the endpoints found in the controller classes found in the com.edmart.vendorservice.controller package.", version, null, new Contact("Developers: Nestor Martourez", "vendor-service.edmart.com", "mail.astraopencollab@gmail.com"), " Apache License, Version 2.0", "https://opensource.org/license/apache-2-0/"));
    }

    @Bean
    public NewTopic vendorKafkaTopic(){
        return TopicBuilder.name("vendor-topic")
                .build();
    }
}
