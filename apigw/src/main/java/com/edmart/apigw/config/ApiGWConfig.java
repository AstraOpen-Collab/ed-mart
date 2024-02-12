//package com.edmart.apigw.config;
//
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Component
//@Primary
//@EnableAutoConfiguration
//public class ApiGWConfig implements SwaggerResourcesProvider {
//
//
//
//    @Override
//    public List get() {
//        List<Object> resources = new ArrayList<>();
//        resources.add(swaggerResource("category-service", "/v2/api-docs/category", "2.0"));
//        resources.add(swaggerResource("vendor-service", "/v2/api-docs/vendor", "2.0"));
//        resources.add(swaggerResource("product-service", "/v2/api-docs/product", "2.0"));
//        return resources;
//    }
//
//    private SwaggerResource swaggerResource(String name, String location, String version) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion(version);
//        return swaggerResource;
//    }
//
//
//}
