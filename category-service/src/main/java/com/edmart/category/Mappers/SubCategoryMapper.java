package com.edmart.category.Mappers;

import com.edmart.category.dto.SubCategoryDTO;
import com.edmart.category.entity.SubCategory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubCategoryMapper implements Function<SubCategory, SubCategoryDTO> {

    @Override
    public SubCategoryDTO apply(SubCategory subCategory) {
        return new SubCategoryDTO(
                subCategory.getName(),
                subCategory.getDescription(),
                subCategory.getCategory()
        );
    }
}

