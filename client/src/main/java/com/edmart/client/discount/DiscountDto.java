package com.edmart.client.discount;

import java.time.LocalDateTime;

public record DiscountDto(
        Long discountId,
        String discountName,
        String discountCode,

        Double percentage,
        Long productId,
        Long categoryId,
        LocalDateTime startDate,
        Integer durationHours
) {
}
