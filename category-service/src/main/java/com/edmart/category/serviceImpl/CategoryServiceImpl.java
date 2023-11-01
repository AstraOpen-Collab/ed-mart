package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.CategoryMapper;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryMapper.INSTANCE.mapToCategory(categoryDTO));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.INSTANCE.mapToCategoryDTOs(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Optional<Category> categoryOptional=categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()){
            return null;
        }
        Category category=categoryOptional.get();
        return CategoryMapper.INSTANCE.mapToCategoryDTO(category);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return;
        }
        Category category = categoryOptional.get();

        CategoryMapper.INSTANCE.mapUpdateToCategory(categoryDTO, category);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional=categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()){
            return;
        }
        categoryRepository.deleteById(categoryId);
    }
}
