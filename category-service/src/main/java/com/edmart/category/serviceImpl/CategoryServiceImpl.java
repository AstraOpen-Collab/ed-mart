package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.CategoryMapper;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        try {
            categoryRepository.save(CategoryMapper.INSTANCE.mapToCategory(categoryDTO));

        }catch (Exception e){
            log.error("Error creating category with name {} caused by {}",categoryDTO.categoryName(),e.getCause());
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper.INSTANCE::mapToCategoryDTO)
                .collect(Collectors.toList());    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> CategoryMapper.INSTANCE.mapToCategoryDTO(category))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (!categoryOptional.isPresent()) {
                throw new CategoryNotFoundException("Category not found");
            }
            Category category = categoryOptional.get();

            CategoryMapper.INSTANCE.mapUpdateToCategory(categoryDTO, category);
            categoryRepository.save(category);
        }catch (Exception e){
            log.error("Error updating category with Id {} caused by {}",categoryId,e.getCause());
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional=categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()){
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }
}
