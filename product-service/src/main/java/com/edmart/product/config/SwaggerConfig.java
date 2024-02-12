package com.edmart.product.config;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Value("${spring-boot.version}")
    private static String version;

    @Bean
    public Docket api() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        //Model model = reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edmart.product.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("Documentation Name: Product Application Service", "This API Documentation provides description to the various endpoints that makes up the Product-Service. It also provides description and ways to use the onsite web tool provided by swagger2 to test all the endpoints found in the controller classes found in the com.edmart.product.controller package.", version, null, new Contact("Developers: Ngoe Kelson Tchinda, Nestor Martourez", "category-service.edmart.com", "mail.astraopencollab@gmail.com"), " Apache License, Version 2.0", "https://opensource.org/license/apache-2-0/"));
    }
}
