package com.edmart.category.dto;

import com.edmart.category.entity.Category;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public record SubCategoryDTO(
        String name,
        String description,

        Category category) implements Serializable {
}