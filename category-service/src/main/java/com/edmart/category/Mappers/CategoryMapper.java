package com.edmart.category.Mappers;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.entity.Category;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.function.Function;


@Service
public class CategoryMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription(),
                category.getCategoryDesignation(),
                category.getSubCategories()
        );
    }
}

