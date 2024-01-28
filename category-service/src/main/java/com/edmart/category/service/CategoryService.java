package com.edmart.category.service;

import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CategoryService {

    void createCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException;

    CategoryResponseDTO getAllCategories(int page, int size, String sortBy, String sortDir);

    Optional<CategoryDTO> getCategory(Long categoryId) throws CategoryNotFoundException;

    void updateCategory(Long categoryId, CategoryDTO categoryDTO) throws CategoryNotFoundException;

    void deleteCategory(Long categoryId) throws CategoryNotFoundException;


}
