package com.edmart.discountservice.service;

import com.edmart.client.discount.DiscountDto;
import com.edmart.discountservice.mapper.DiscountDtoMapper;
import com.edmart.discountservice.model.Discount;
import com.edmart.discountservice.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService{

    private final DiscountRepository discountRepository;

    private final DiscountDtoMapper discountDtoMapper;


    @Override
    public List<DiscountDto> getAllDiscounts() {
        log.info("Getting all available discounts");
        return discountRepository.findAll()
                .stream().map(discountDtoMapper).collect(Collectors.toList());
    }


    @Override
    public void createNewDiscount(DiscountDto discountDto) throws Exception {
        try{
            var discount = setDiscountProperties(discountDto);

            //log.info("Creating new discount : {}", discount);
            discountRepository.save(discount);
        }catch(Exception exception){
            log.error("Error creating discount with cause: {}", exception.getCause());
            throw new Exception("Error creating discount!");
        }

    }

    //Set discount properties

    private Discount setDiscountProperties(DiscountDto discountDto){
        Discount discount = new Discount();

        String discountCode = generateUniqueDiscountCode();

        discount.setDiscountName(discountDto.discountName());
        discount.setDiscountCode(discountCode);
        discount.setPercentage(discountDto.percentage());
        discount.setProductId(discountDto.productId());
        discount.setCategoryId(discountDto.categoryId());
        discount.setCreatedAt(discountDto.startDate());
        discount.setDurationHours(discountDto.durationHours());

        return discount;
    }

    private String generateUniqueDiscountCode() {
        String discountCode;
        do {
            // Generate a random alphanumeric string of length 6
            discountCode = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        } while (discountRepository.existsByDiscountCode(discountCode));

        return discountCode;
    }

    @Override
    public DiscountDto getDiscountByCode(String discountCode) {
        return null;
    }
}
