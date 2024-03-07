package com.edmart.discountservice.service;

import com.edmart.client.discount.DiscountDto;

import java.util.List;

public interface DiscountService {

    List<DiscountDto> getAllDiscounts();

    void createNewDiscount(DiscountDto discountDto) throws Exception;

    DiscountDto getDiscountByCode(String discountCode);
}
