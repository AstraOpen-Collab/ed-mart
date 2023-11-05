package com.edmart.category.service;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    void createCategory(CategoryDTO categoryDTO);

    CategoryResponseDTO getAllCategories(int page, int size);

    CategoryDTO getCategory(Long categoryId);

    void updateCategory(Long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);
}
