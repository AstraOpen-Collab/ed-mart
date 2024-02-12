//package com.edmart.apigw.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/fb")
//public class FallbackController {
//
//    @PostMapping(value = "/category")
//    public ResponseEntity<HttpStatus> categoryFallback() {
//        return ResponseEntity.ok(HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    @PostMapping(value = "/vendor")
//    public ResponseEntity<HttpStatus> vendorFallback() {
//        return ResponseEntity.ok(HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    @PostMapping(value = "/product")
//    public ResponseEntity<HttpStatus> productFallback() {
//        return ResponseEntity.ok(HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    @PostMapping(value = "/inventory")
//    public ResponseEntity<HttpStatus> inventoryFallback() {
//        return ResponseEntity.ok(HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//}
