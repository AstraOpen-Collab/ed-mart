package com.edmart.elasticsearch.controller;

import com.edmart.client.product.ProductDTO;
import com.edmart.elasticsearch.model.Product;
import com.edmart.elasticsearch.service.ElasticSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search/products")
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:8089"} )
public class ElasticsearchController {

    private final ElasticSearchServiceImpl elasticSearchService;

    @GetMapping
    public Iterable<Product> getAllProductIndexes(){
        return elasticSearchService.getAllProductIndexes();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProductByProductId(@PathVariable("productId") Long productId){

        return ResponseEntity.ok().body(elasticSearchService.getProductByProductId(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductIndexById(@PathVariable("productId") Long productId){
        elasticSearchService.deleteElasticIndexByProductId(productId);

        return ResponseEntity.ok().body("Product Index deleted Successfully!");
    }
}
