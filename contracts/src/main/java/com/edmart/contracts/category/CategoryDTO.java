package com.edmart.contracts.category;

import java.io.Serializable;
import java.util.List;

public record CategoryDTO(
        Long categoryId,
        String categoryName,
        String categoryDescription,
        String categoryDesignation,
        List<SubCategoryDTO> subCategories) implements Serializable {}