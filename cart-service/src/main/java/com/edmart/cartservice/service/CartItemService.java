package com.edmart.cartservice.service;

import com.edmart.cartservice.entity.CartItem;
import com.edmart.client.product.ProductDTO;

import java.util.List;

public interface CartItemService {

    void addItemToCart(Long productId);

    void removeItemFromCart(Long productId);

    List<CartItem> getCartItems();
}
