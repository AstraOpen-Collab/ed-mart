package com.edmart.category.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CategorySwaggerConfig {

    @Value("${spring-boot.version}")
    private static String version;

//    @Bean
//    public Docket api() throws IOException, XmlPullParserException {
//        MavenXpp3Reader reader = new MavenXpp3Reader();
//        //Model model = reader.read(new FileReader("pom.xml"));
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.edmart"))
//                .paths(PathSelectors.any())
//                .build().apiInfo(new ApiInfo("Documentation Name: Category Service", "This API Documentation provides description to the various endpoints that makes up the Category-Service. It also provides description and ways to use the onsite web tool provided by swagger2 to test all the endpoints found in the controller classes found in the com.edmart.category.controller package.", version, null, new Contact("Developers: Ngoe Kelson Tchinda, Nobert Etta", "category-service.edmart.com", "mail.astraopencollab@gmail.com"), " Apache License, Version 2.0", "https://opensource.org/license/apache-2-0/"))
//                .pathMapping("/")
//                .useDefaultResponseMessages(false)
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class);
//
//    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("category-service")
                .packagesToScan("com.edmart.category.controller")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Category Service")
                        .description("Microservice to handle creation of various Categories")
                        .contact(contact())
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    public Contact contact(){
        return new Contact()
                .name("Developers: Ngoe Kelson Tchinda, Nobert Etta")
                .url("category-service.edmart.com")
                .email("mail.astraopencollab@gmail.com");
    }

}
