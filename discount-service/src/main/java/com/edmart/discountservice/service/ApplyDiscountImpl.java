package com.edmart.discountservice.service;

import com.edmart.client.exceptions.DiscountNotFoundException;
import com.edmart.contracts.category.CategoryDiscountEvent;
import com.edmart.contracts.product.ProductDiscountEvent;
import com.edmart.discountservice.model.Discount;
import com.edmart.discountservice.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyDiscountImpl implements ApplyDiscount{

    private final DiscountRepository discountRepository;

    private final KafkaTemplate<String, ?> kafkaTemplate;


    @Override
    public void applyDiscountToProduct(Long productId, String discountCode) throws DiscountNotFoundException {

        Optional<Discount> discount = Optional.ofNullable(discountRepository.findDiscountByDiscountCode(discountCode));

        if(discount.isPresent()){
            discount.get().setProductId(productId);
            sendProductDiscountEvent(setProductDiscountEventValues(discount.get()));
            log.info("Successfully sent discount message to discount topic....");
        }else{
            log.error("Error sending discount message to discount topic!....Discount not found!");
            throw new DiscountNotFoundException("Discount with code does not exist!");
        }
    }

    @Override
    public void applyDiscountToCategory(Long categoryId, String discountCode) throws DiscountNotFoundException {
        Optional<Discount> discount = Optional.ofNullable(discountRepository.findDiscountByDiscountCode(discountCode));

        if(discount.isPresent()){
            discount.get().setCategoryId(categoryId);
            sendCategoryDiscountEvent(setCategoryDiscountEventValues(discount.get()));
            log.info("Successfully sent category discount message to discount topic....");
        }else{
            log.error("Error sending category discount message to discount topic!....Discount not found!");
            throw new DiscountNotFoundException("Discount with code does not exist!");
        }
    }

    //set discount object with the neccessary values

    public ProductDiscountEvent setProductDiscountEventValues(Discount discount){
        ProductDiscountEvent productDiscountEvent = new ProductDiscountEvent();

        productDiscountEvent.setProductId(discount.getProductId());
        productDiscountEvent.setDiscountCode(discount.getDiscountCode());
        productDiscountEvent.setPercentage(discount.getPercentage());
        productDiscountEvent.setHours(discount.getDurationHours());

        return productDiscountEvent;
    }

    public CategoryDiscountEvent setCategoryDiscountEventValues(Discount discount){
        CategoryDiscountEvent categoryDiscountEvent = new CategoryDiscountEvent();

        categoryDiscountEvent.setCategoryId(discount.getProductId());
        categoryDiscountEvent.setDiscountCode(discount.getDiscountCode());
        categoryDiscountEvent.setPercentage(discount.getPercentage());
        categoryDiscountEvent.setHours(discount.getDurationHours());

        return categoryDiscountEvent;
    }


    public void sendProductDiscountEvent(ProductDiscountEvent productDiscountEvent) {
        log.info("Sending new Discount message => : {}", productDiscountEvent);

        Message<ProductDiscountEvent> payload = MessageBuilder
                .withPayload(productDiscountEvent)
                .setHeader(KafkaHeaders.TOPIC,"product_discount_topic")
                .setHeader(KafkaHeaders.MESSAGE_KEY, generateShortUUID())
                .build();

        kafkaTemplate.send(payload);
    }

    public void sendCategoryDiscountEvent(CategoryDiscountEvent categoryDiscountEvent) {
        log.info("Sending new category Discount message => : {}", categoryDiscountEvent);

        Message<CategoryDiscountEvent> payload = MessageBuilder
                .withPayload(categoryDiscountEvent)
                .setHeader(KafkaHeaders.TOPIC,"category_discount_topic")
                .setHeader(KafkaHeaders.MESSAGE_KEY, generateShortUUID())
                .build();

        kafkaTemplate.send(payload);
    }

    public static String generateShortUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return uuid;
    }
}
