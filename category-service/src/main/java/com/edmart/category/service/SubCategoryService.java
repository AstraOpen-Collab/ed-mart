package com.edmart.category.service;

import com.edmart.category.dto.SubCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SubCategoryService {

    List<SubCategoryDTO> getAllSubCategories();

    List<SubCategoryDTO> getSubCategoriesInCategory(Long categoryId);

    void createSubCategory(SubCategoryDTO request);

    Optional<SubCategoryDTO> getSubCategoryById(Long id);

    void updateSubCategory(Long id, SubCategoryDTO request);

    void deleteSubCategory(Long id);
}
