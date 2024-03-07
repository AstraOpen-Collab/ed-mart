package com.edmart.product.serviceImpl;


import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.contracts.category.CategoryDiscountEvent;
import com.edmart.contracts.product.ProductDiscountEvent;
import com.edmart.product.mappers.ProductMapper;
import com.edmart.product.model.Product;
import com.edmart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountListenerService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final DiscountCountDownTimerService discountCountDownTimerService;



    @KafkaListener(
            topics = "product_discount_topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeProductDiscountEvent(ProductDiscountEvent productDiscountEvent){
        Optional<Product> product = productRepository.findById(
                productDiscountEvent.getProductId());
        if(product.isPresent()){
            log.info("Setting Product Discount!!");


            product.get().getPrices().setDiscountPrice(
                    getDiscountPrice(productDiscountEvent.getPercentage(),
                            product.get().getPrices().getNewPrice()));

            product.get().setSetDiscount(true);

            productRepository.save(product.get());
            log.info("Successfully set discount for product with Id: {}", productDiscountEvent.getProductId());

            log.info("Starting timer for this discount...");
            discountCountDownTimerService.startTimer(productDiscountEvent.getHours(), productDiscountEvent.getProductId());

        }else{
            log.error("Product does not exist!!");
            System.exit(0);
            throw new ProductNotFoundException("Product does not Exist!!");
        }
    }


    @KafkaListener(
            topics = "category_discount_topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeCategoryDiscountEvent(CategoryDiscountEvent categoryDiscountEvent){
        List<Product> products = productRepository.findAllByCategoryId(categoryDiscountEvent.getCategoryId());
        if(!products.isEmpty()){
            log.info("Setting Product Discount!!");

            for(Product item : products){
                item.getPrices().setDiscountPrice(getDiscountPrice(categoryDiscountEvent.getPercentage(), item.getPrices().getNewPrice()));
                item.setSetDiscount(true);

                productRepository.save(item);
            }

            log.info("Successfully set discount for all products with Category Id: {}", categoryDiscountEvent.getCategoryId());

            log.info("Starting timer for this discount...");
            discountCountDownTimerService.startCategoryDiscountTimer(categoryDiscountEvent.getHours(), categoryDiscountEvent.getCategoryId());

        }else{
            log.error("Category does not exist!!");
            System.exit(0);
            throw new ProductNotFoundException("Category does not Exist!!");
        }
    }


    public BigDecimal getDiscountPrice(Double discount, BigDecimal price){
        BigDecimal discountPrice = BigDecimal.valueOf(discount/100 * price.doubleValue());

        return price.subtract(discountPrice);
    }

}
