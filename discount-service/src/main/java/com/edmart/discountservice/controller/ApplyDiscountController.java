package com.edmart.discountservice.controller;


import com.edmart.client.exceptions.DiscountNotFoundException;
import com.edmart.discountservice.service.ApplyDiscountImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apply")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:8091"} )
public class ApplyDiscountController {

    private final ApplyDiscountImpl applyDiscount;

    @GetMapping("/products/{productId}")
    public ResponseEntity<String> applyProductDiscount(@PathVariable("productId") Long productId,
                                                       @RequestParam("code") String code) throws DiscountNotFoundException {

        applyDiscount.applyDiscountToProduct(productId, code);

        return  ResponseEntity.ok().body("Discount Successfully Set!!");
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<String> applyCategoryDiscount(@PathVariable("categoryId") Long categoryId,
                                                       @RequestParam("code") String code) throws DiscountNotFoundException {

        applyDiscount.applyDiscountToCategory(categoryId, code);

        return  ResponseEntity.ok().body("Discount Successfully Set to this Category");
    }
}
