package com.edmart.category.controller;

import com.edmart.category.dto.SubCategoryDTO;
import com.edmart.category.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sub-categories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> getAllSubCategories() {
        log.info("Retrieving all sub-categories");

        return ResponseEntity.ok(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<SubCategoryDTO>> getSubCategoriesInCategory(@PathVariable Long categoryId) {
        log.info("Retrieving all sub-categories in the category with id: {}", categoryId);

        return ResponseEntity.ok(subCategoryService.getSubCategoriesInCategory(categoryId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{categoryId}")
    public ResponseEntity<String> createSubCategory(@PathVariable Long categoryId,
                                                    @RequestBody SubCategoryDTO subCategoryRequest) {
        log.info("Creating a sub category with name: {} under the category with id: {}",
                subCategoryRequest.name(), categoryId);

        subCategoryService.createSubCategory(categoryId, subCategoryRequest);

        return ResponseEntity.ok("Sub category created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> getSubCategoryById(@PathVariable Long id) {
        log.info("Retrieving the sub category with id: {}", id);

        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryDTO request) {
        log.info("Updating the sub category with id: {}", id);

        subCategoryService.updateSubCategory(id, request);

        log.info("Updated Category Successfully {}", request);

        return ResponseEntity.ok("Sub category updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable Long id) {
        subCategoryService.deleteSubCategory(id);

        log.info("Successfully deleted the sub category with id {}", id);

        return ResponseEntity.ok("Sub category deleted successfully");
    }
}
