package com.edmart.category.serviceImpl;

import com.edmart.category.Mappers.CategoryMapper;
import com.edmart.category.Mappers.SubCategoryMapper;
import com.edmart.category.client.CommonOperations;
import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.SubCategoryDTO;
import com.edmart.category.entity.Category;
import com.edmart.category.entity.SubCategory;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.exception.SubCategoryNotFoundException;
import com.edmart.category.repository.CategoryRepository;
import com.edmart.category.repository.SubCategoryRepository;
import com.edmart.category.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    private final SubCategoryMapper subCategoryMapper;

    private final CategoryMapper categoryMapper;
    private final CommonOperations commonOperations;

    @Override
    //@Cacheable(cacheNames = "subcategory")
    public List<SubCategoryDTO> getAllSubCategories() {
        return subCategoryRepository.findAll().stream()
                .map(subCategoryMapper).toList();
    }

    @Override
    public List<SubCategoryDTO> getSubCategoriesInCategory(Long categoryId) {
        return fetchCategoryById(categoryId)
                .getSubCategories().stream().map(subCategoryMapper).toList();
    }

    @Override
    //@CacheEvict(value = "subcategory", allEntries = true)
    public void createSubCategory(SubCategoryDTO request) {
        SubCategory subCategory = new SubCategory();
        Category category = fetchCategoryById(request.category().getCategoryId());

        try {
            subCategory.setName(request.name());
            subCategory.setDescription(request.description());
            subCategory.setCategory(request.category());
            subCategory.setCategory(category);

            subCategoryRepository.save(subCategory);
        } catch (Exception e) {
            log.error("Failed to create sub category due to: {}", e.getMessage());
            throw new RuntimeException("Failed to create sub category");
        }
    }

    @Override
    //@Cacheable(cacheNames = "subcategory", key = "#id")
    public Optional<SubCategoryDTO> getSubCategoryById(Long id) {

        return Optional.ofNullable(subCategoryRepository
                .findById(id)
                .map(subCategoryMapper)
                .orElseThrow(() -> new SubCategoryNotFoundException("Subcategory with this Id does not exist!!")));
    }

    @Override
    //@CachePut(cacheNames = "subcategory", key = "#id")
    public void updateSubCategory(Long id, SubCategoryDTO request) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(
                SubCategoryNotFoundException::new);
        try {
            BeanUtils.copyProperties(request, subCategory, commonOperations.getNullPropertyNames(request));
            subCategoryRepository.save(subCategory);
        } catch (Exception e) {
            log.error("Failed to update sub category due to: {}", e.getMessage());
            throw new RuntimeException("Failed to update sub category");
        }
    }

    @Override
    //@CacheEvict(cacheNames = "subcategory", key = "#id", beforeInvocation = true)
    public void deleteSubCategory(Long id) {
        subCategoryRepository.findById(id).orElseThrow(
                SubCategoryNotFoundException::new);
        subCategoryRepository.deleteById(id);
    }

    private Category fetchCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found"));
    }

}
