package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.CategoryMapper;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.service.CategoryService;
import com.edmart.category.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    @Override
    public void createCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException {
        Category category = new Category();
        try{
            category.setCategoryName(categoryDTO.categoryName());
            category.setCategoryDescription(categoryDTO.categoryDescription());
            category.setCategoryDesignation(categoryDTO.categoryDesignation());

            categoryRepository.save(category);
        }catch(Exception e){
            log.error("Error Creating category with name {} caused by {}", categoryDTO.categoryName(), e.getCause());
        }
    }

    @Override
    public CategoryResponseDTO getAllCategories(int page, int size,String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        CategoryResponseDTO categoryResponseDto=new CategoryResponseDTO();
        categoryResponseDto.setCategoryList(categoryPage.getContent());
        categoryResponseDto.setPageInfoDTO(Pagination.getPageInfoDTO(categoryPage));

        return  categoryResponseDto;
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) throws CategoryNotFoundException {
        Optional<CategoryDTO> categoryDTOOptional = categoryRepository.findById(categoryId)
                .map(categoryMapper);
        if(categoryDTOOptional.isPresent()){
            return categoryDTOOptional.get();
        }else{
            throw new CategoryNotFoundException("No Category exist with this Id");
        }
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws CategoryNotFoundException {
        try{
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(()->new CategoryNotFoundException("Category does not exist"));

            BeanUtils.copyProperties(categoryDTO, category, getNullPropertyNames(categoryDTO));

            categoryRepository.save(category);
        }catch(Exception ex){
            log.error("Error updating category with name {} caused by {}", categoryDTO.categoryName(), ex.getMessage());
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryNotFoundException {
        Optional<CategoryDTO> categoryDTOOptional = categoryRepository.findById(categoryId).map(categoryMapper);

        if(categoryDTOOptional.isPresent()){
            categoryRepository.deleteById(categoryId);
        }else{
            throw new CategoryNotFoundException("Category with this Id does not exist");
        }
    }

    private String[] getNullPropertyNames(CategoryDTO categoryDTO) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(categoryDTO);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }
    }
