package com.edmart.contracts.category;

import java.io.Serializable;

public record SubCategoryDTO(
        String name,
        String description,

        CategoryDTO categoryDTO) implements Serializable {
}