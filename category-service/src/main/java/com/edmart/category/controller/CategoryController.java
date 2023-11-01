package com.edmart.category.controller;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryRequest){
        categoryService.createCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("Id") Long Id){
        return ResponseEntity.ok().body(categoryService.getCategory(Id));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO request, @PathVariable Long categoryId){
        categoryService.updateCategory(categoryId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
