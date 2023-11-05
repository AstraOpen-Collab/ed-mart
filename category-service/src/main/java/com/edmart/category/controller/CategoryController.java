package com.edmart.category.controller;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
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
    public ResponseEntity<CategoryResponseDTO> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        log.info("Retrieving all categories: {}", categoryService.getAllCategories(page, size));

        return ResponseEntity.ok().body(categoryService.getAllCategories(page, size));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryRequest){
        log.info("Creating a category with name: {}",categoryRequest.categoryName());

        categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok().body("Category created successfully");
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("Id") Long Id){
        log.info("Retrieving a category with Id: {}", Id);

        return ResponseEntity.ok().body(categoryService.getCategory(Id));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO request, @PathVariable Long categoryId){
        log.info("Updating a category with name:{}",request.categoryName());

        categoryService.updateCategory(categoryId, request);

        log.info("Updated Category Successfully {}", request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);

        log.info("Successfully deleted category with Id {}", categoryId);

        return ResponseEntity.ok("Category deleted successfully!");
    }
}
