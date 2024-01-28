package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.CategoryMapper;
import com.edmart.category.client.CommonOperations;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final CommonOperations commonOperations;

    private final NewTopic topic;
    private final KafkaTemplate<String, Boolean> kafkaTemplate;

    @Override
    //@CacheEvict(value = "category", allEntries = true)
    public void createCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException {
        Category category = new Category();
        try {
            category.setCategoryName(categoryDTO.categoryName());
            category.setCategoryDescription(categoryDTO.categoryDescription());
            category.setCategoryDesignation(categoryDTO.categoryDesignation());
            categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Error Creating category with name {} caused by {}", categoryDTO.categoryName(), e.getCause());
        }
    }

    @Override
    //@Cacheable(cacheNames = "category")
    public CategoryResponseDTO getAllCategories(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        CategoryResponseDTO categoryResponseDto = new CategoryResponseDTO();

        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryDTO> categories = categoryRepository.findAll().stream()
                .map(categoryMapper)
                .collect(Collectors.toList());

        categoryResponseDto.setContent(categories);
        categoryResponseDto.setPageNo(categoryPage.getNumber());
        categoryResponseDto.setPageSize(categoryPage.getSize());
        categoryResponseDto.setTotalElements(categoryPage.getTotalElements());

        return categoryResponseDto;
    }

    @Override
    //@Cacheable(cacheNames = "category", key = "#p0")
    public Optional<CategoryDTO> getCategory(Long categoryId) throws CategoryNotFoundException {
        return Optional.ofNullable(categoryRepository.findById(categoryId)
                .map(categoryMapper).orElseThrow(() -> new CategoryNotFoundException("Category does not exist!!")));
    }

    @Override
    //@CachePut(cacheNames = "category", key = "#p0")
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        try{
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(()->new CategoryNotFoundException("Category does not exist"));

            BeanUtils.copyProperties(categoryDTO, category, commonOperations.getNullPropertyNames(categoryDTO));

            categoryRepository.save(category);
        }catch(Exception ex){
            log.error("Error updating category with name {} caused by {}", categoryDTO.categoryName(), ex.getMessage());
        }
    }

    @Override
    //@CacheEvict(cacheNames = "category", key = "#p0", beforeInvocation = true)
    public void deleteCategory(Long categoryId) throws CategoryNotFoundException {
        Optional<CategoryDTO> categoryDTOOptional = categoryRepository.findById(categoryId).map(categoryMapper);

        if(categoryDTOOptional.isPresent()){
            categoryRepository.deleteById(categoryId);
        }else{
            throw new CategoryNotFoundException("Category with this Id does not exist");
        }
    }

    public Boolean checkCategoryAvailability(Long categoryId){

        log.info("Checking existence of category with Id: {}", categoryId);
        Optional<CategoryDTO> categoryDTO = Optional.of(
                categoryRepository.findById(categoryId).map(categoryMapper).get());
        if(categoryDTO.get().categoryId().equals(categoryId)){
            return true;
        }
        return false;
    }

//    @KafkaListener(
//            topics = "product-topic",
//            groupId = "${spring.kafka.consumer.group-id}"
//    )
//    public void consumeProductEvent(Long Id){
//
//        Boolean check_variable = true;
//        Optional<CategoryDTO> category = Optional.of(categoryRepository
//                .findById(Id).map(categoryMapper).get());
//        if(category.get().categoryId().equals(Id)){
//            sendMessage(check_variable);
//        }else{
//            throw new CategoryNotFoundException("Category with this Id does not exist!!");
//        }
//
//    }

    public void sendMessage(Boolean check_variable){
        log.info("Check variable is: {}", check_variable);
        Message<Boolean> message = MessageBuilder
                .withPayload(check_variable)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
    }
