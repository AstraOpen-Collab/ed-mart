package com.edmart.discountservice.mapper;

import com.edmart.client.discount.DiscountDto;
import com.edmart.discountservice.model.Discount;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DiscountDtoMapper implements Function<Discount, DiscountDto> {

    @Override
    public DiscountDto apply(Discount discount) {
        return new DiscountDto(
                discount.getDiscountId(),
                discount.getDiscountName(),
                discount.getDiscountCode(),
                discount.getPercentage(),
                discount.getProductId(),
                discount.getCategoryId(),
                discount.getStartDate(),
                discount.getDurationHours()
        );
    }
}
