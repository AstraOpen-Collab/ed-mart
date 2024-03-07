package com.edmart.discountservice.repository;

import com.edmart.discountservice.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    boolean existsByDiscountCode(String discountCode);

    Discount findDiscountByDiscountCode(String discountCode);
}
