package com.edmart.discountservice.service;

import com.edmart.client.exceptions.DiscountNotFoundException;

public interface ApplyDiscount {

    void  applyDiscountToProduct(Long productId, String discountCode) throws DiscountNotFoundException;

    void applyDiscountToCategory(Long categoryId, String discountCode) throws DiscountNotFoundException;
}
