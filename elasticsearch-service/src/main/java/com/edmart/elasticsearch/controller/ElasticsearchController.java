package com.edmart.elasticsearch.controller;

import com.edmart.client.product.ProductDTO;
import com.edmart.elasticsearch.service.ElasticSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class ElasticsearchController {

    private final ElasticSearchServiceImpl elasticSearchService;

    @PostMapping("{vendorId}")
    public ResponseEntity<String> saveNewProduct(@PathVariable("vendorId") Long vendorId, @RequestBody ProductDTO product){
        elasticSearchService.vendorCreateProduct(vendorId, product);
        return ResponseEntity.ok().body("Item saved to elasticsearch successfully!");
    }
}
