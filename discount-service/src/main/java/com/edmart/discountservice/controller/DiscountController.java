package com.edmart.discountservice.controller;


import com.edmart.client.discount.DiscountDto;
import com.edmart.discountservice.service.DiscountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:8091"} )
public class DiscountController {

    private final DiscountServiceImpl discountService;

    @GetMapping
    public ResponseEntity<List<DiscountDto>> getAllDiscounts(){
        log.info("Successfully retrieved all the discounts");
        return ResponseEntity.ok().body(discountService.getAllDiscounts());
    }

    @PostMapping
    public ResponseEntity<String> createNewVendor(@RequestBody DiscountDto discountDto) throws Exception {
        log.info("creating new discount: { }", discountDto);

        discountService.createNewDiscount(discountDto);

        return ResponseEntity.ok().body("Successfully created new discount");
    }
}
