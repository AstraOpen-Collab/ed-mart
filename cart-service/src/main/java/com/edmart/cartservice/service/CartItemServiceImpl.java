package com.edmart.cartservice.service;

import com.edmart.cartservice.entity.CartItem;
import com.edmart.cartservice.repository.CartItemRepository;
import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;

    private final ProductServiceClient productServiceClient;

    private final List<ProductDTO> cart = new ArrayList<>();

    @Override
    public void addItemToCart(Long productId) {

        try{
            cart.add(productServiceClient.getProductById(productId).getBody());
            log.info("Product with Id: {} added to cart: {}", productId, cart);
            System.out.println(cart);

        }catch(ProductNotFoundException productNotFoundException){
            log.error("Product Not Found!: {}", productNotFoundException.getMessage());
        }
    }

    @Override
    public void removeItemFromCart(Long productId) {
        try{
            cart.remove(productServiceClient.getProductById(productId).getBody());
            log.info("Item with Id: {} is removed successfully!: {}", productId, cart);
        }catch(ProductNotFoundException productNotFoundException){
            log.error("Item does not exist in Cart!");
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll().stream().toList();
    }
}
