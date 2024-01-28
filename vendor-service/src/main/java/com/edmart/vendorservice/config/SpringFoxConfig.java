package com.edmart.vendorservice.config;//package com.astraworks.vendor.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.hateoas.client.LinkDiscoverer;
//import org.springframework.hateoas.client.LinkDiscoverers;
//import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
//import org.springframework.plugin.core.SimplePluginRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class SpringFoxConfig extends WebMvcConfigurationSupport {
//
//    @Bean
//    public LinkDiscoverers discoverers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//
//    }
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.astraworks.vendor.controllers")).build()
//                .apiInfo(metaData());
//
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("Spring Boot Redis Cache")
//                .description("\"Spring Boot REST API for Spring Boot Redis Cache\"")
//                .version("0.0.1")
//                //.license("Apache License Version 2.0")
//                //.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .contact(new Contact("Nestor Martourez", "https://github.com/coderkan", "nmartourez.astraworks@gmail.com"))
//                .build();
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}
