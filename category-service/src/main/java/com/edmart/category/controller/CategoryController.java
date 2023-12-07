package com.edmart.category.controller;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponseDTO> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                                @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        log.info("Retrieving all categories: {}", categoryService.getAllCategories(page, size,sortBy, sortDir));

        return ResponseEntity.ok().body(categoryService.getAllCategories(page, size,sortBy, sortDir));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryRequest) throws CategoryNotFoundException {
        log.info("Creating a category with name: {}",categoryRequest.categoryName());

        categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok().body("Category created successfully");
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("Id") Long Id) throws CategoryNotFoundException {
        log.info("Retrieving a category with Id: {}", Id);

        return ResponseEntity.ok().body(categoryService.getCategory(Id));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO request, @PathVariable Long categoryId) throws CategoryNotFoundException {
        log.info("Updating a category with name:{}",request.categoryName());

        categoryService.updateCategory(categoryId, request);

        log.info("Updated Category Successfully {}", request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) throws CategoryNotFoundException {
        categoryService.deleteCategory(categoryId);

        log.info("Successfully deleted category with Id {}", categoryId);

        return ResponseEntity.ok("Category deleted successfully!");
    }
}
