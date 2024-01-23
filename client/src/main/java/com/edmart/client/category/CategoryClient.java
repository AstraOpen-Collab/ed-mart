package com.edmart.client.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "category",
        path = "/api/v1/categories"
)
public interface CategoryClient {

    @GetMapping("/check/{Id}")
    ResponseEntity<Boolean> checkCategoryById(@PathVariable("Id") Long categoryId);

}
