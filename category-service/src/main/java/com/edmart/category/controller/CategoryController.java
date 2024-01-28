package com.edmart.category.controller;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.dto.CategoryResponseDTO;
import com.edmart.category.exception.CategoryNotFoundException;
import com.edmart.category.service.CategoryService;
import com.edmart.category.serviceImpl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponseDTO> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                                @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        log.info("Retrieving all categories");

        return ResponseEntity.ok().body(categoryService.getAllCategories(page, size,sortBy, sortDir));
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryRequest) throws CategoryNotFoundException {
        log.info("Creating a category with name: {}",categoryRequest.categoryName());

        categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok().body("Category created successfully");
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Optional<CategoryDTO>> getCategoryById(@PathVariable("Id") Long Id) throws CategoryNotFoundException {
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

    @GetMapping("/check/{Id}")
    public ResponseEntity<Optional<Long>> checkCategoryById(@PathVariable("Id") Long Id){
        Optional<Long> categoryId = Optional.ofNullable(categoryService.getCategory(Id).get().categoryId());
        if(categoryId.isEmpty()){
            throw new CategoryNotFoundException("Category Not Found!!..");
        }
        return ResponseEntity.ok().body(categoryId);
    }
}
