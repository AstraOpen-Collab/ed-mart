package com.edmart.category.service;

import com.edmart.category.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    void createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategory(Long categoryId);

    void updateCategory(Long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);
}
