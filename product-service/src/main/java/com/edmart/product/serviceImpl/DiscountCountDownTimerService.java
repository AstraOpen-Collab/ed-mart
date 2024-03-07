package com.edmart.product.serviceImpl;


import com.edmart.product.model.Product;
import com.edmart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
//@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class DiscountCountDownTimerService {

    private final ProductRepository productRepository;
    //private final Product product = new Product();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    //Discount count down timer. It accepts time in minutes as a parameter
    @Async
    public void startTimer(int minutes, Long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            Runnable countdownTask = () -> {
                product.setSetDiscount(false);
                product.getPrices().setDiscountPrice(BigDecimal.ZERO);
                productRepository.save(product);
                log.warn("Discount is Expired!!");
                System.out.println("Discount expired for product: " + product.getName());
            };

            scheduler.schedule(countdownTask, minutes, TimeUnit.MINUTES);
        } catch (RejectedExecutionException e) {
            log.warn("Failed to schedule timer: Executor is terminated", e);
        }
    }

    @Async
    public void startCategoryDiscountTimer(int minutes, Long categoryId) {
        try {
            List<Product> products = productRepository.findAllByCategoryId(categoryId);
            Runnable countdownTask = () -> {
                for(Product item: products){
                    item.setSetDiscount(false);
                    item.getPrices().setDiscountPrice(BigDecimal.ZERO);
                    productRepository.save(item);
                }

                log.warn("Category Discount is Expired!!");
                System.out.println("Discount expired for all products with category Id: " +categoryId);
            };

            scheduler.schedule(countdownTask, minutes, TimeUnit.MINUTES);
        } catch (RejectedExecutionException e) {
            log.warn("Failed to schedule timer: Executor is terminated", e);
        }
    }

}
