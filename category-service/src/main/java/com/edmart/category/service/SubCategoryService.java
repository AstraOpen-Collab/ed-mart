package com.edmart.category.service;

import com.edmart.category.dto.SubCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubCategoryService {

    List<SubCategoryDTO> getAllSubCategories();

    List<SubCategoryDTO> getSubCategoriesInCategory(Long categoryId);

    void createSubCategory(Long categoryId, SubCategoryDTO subCategoryRequest);

    SubCategoryDTO getSubCategoryById(Long id);

    void updateSubCategory(Long id, SubCategoryDTO request);

    void deleteSubCategory(Long id);
}
