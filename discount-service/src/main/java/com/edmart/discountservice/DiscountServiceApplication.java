package com.edmart.discountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "com.edmart.contracts"
)
@EnableEurekaClient
public class DiscountServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(DiscountServiceApplication.class, args);
    }
}
