package com.edmart.cartservice.controller;

import com.edmart.cartservice.entity.CartItem;
import com.edmart.cartservice.service.CartItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartItems")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:8092"} )
public class CartItemController {

    private final CartItemServiceImpl cartItemService;


    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(){

        return ResponseEntity.ok().body(cartItemService.getCartItems());
    }

    @GetMapping("/add")
    public ResponseEntity<String> addCartItem(@RequestParam Long productId){

        cartItemService.addItemToCart(productId);
        return ResponseEntity.ok().body("Item Added to Cart!");
    }

}
