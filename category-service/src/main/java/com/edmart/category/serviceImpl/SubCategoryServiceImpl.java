package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.SubCategoryMapper;
import com.edmart.category.dto.SubCategoryDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.entity.SubCategory;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.repository.SubCategoryRepository;
import com.edmart.category.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<SubCategoryDTO> getAllSubCategories() {
        return subCategoryRepository.findAll().stream()
                .map(SubCategoryMapper.INSTANCE::mapToSubCategoryDTO).toList();
    }

    @Override
    public List<SubCategoryDTO> getSubCategoriesInCategory(Long categoryId) {
        return fetchCategoryById(categoryId)
                .getSubCategories().stream().map(SubCategoryMapper.INSTANCE::mapToSubCategoryDTO).toList();
    }

    @Override
    public void createSubCategory(Long categoryId, SubCategoryDTO request) {
        Category category = fetchCategoryById(categoryId);
        try {
            category.getSubCategories()
                    .add(SubCategoryMapper.INSTANCE.mapToSubCategory(request));
            categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Failed to create sub category due to: {}", e.getMessage());
            throw new RuntimeException("Failed to create sub category");
        }
    }

    @Override
    public SubCategoryDTO getSubCategoryById(Long id) {
        return SubCategoryMapper.INSTANCE.mapToSubCategoryDTO(fetchSubCategoryById(id));
    }

    @Override
    public void updateSubCategory(Long id, SubCategoryDTO request) {
        SubCategory subCategory = fetchSubCategoryById(id);
        try {
            SubCategoryMapper.INSTANCE.mapUpdateToSubCategory(request, subCategory);
            subCategoryRepository.save(subCategory);
        } catch (Exception e) {
            log.error("Failed to update sub category due to: {}", e.getMessage());
            throw new RuntimeException("Failed to update sub category");
        }
    }

    @Override
    public void deleteSubCategory(Long id) {
        fetchSubCategoryById(id);
        subCategoryRepository.deleteById(id);
    }

    private SubCategory fetchSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
    }

    private Category fetchCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
    }
}
