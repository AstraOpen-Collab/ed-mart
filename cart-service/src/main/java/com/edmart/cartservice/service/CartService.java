package com.edmart.cartservice.service;

import com.edmart.cartservice.entity.Cart;

public interface CartService {

    void checkOutCart(Cart cart);

    Cart viewCart(Long cartId);
}
