package com.edmart.category.service;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import com.edmart.category.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    void createCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException;

    CategoryResponseDTO getAllCategories(int page, int size,String sortBy, String sortDir);

    CategoryDTO getCategory(Long categoryId) throws CategoryNotFoundException;

    void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws CategoryNotFoundException;

    void deleteCategory(Long categoryId) throws CategoryNotFoundException;
}
