package com.edmart.category.Mappers;

import com.edmart.category.dto.CategoryDTO;
import com.edmart.category.entity.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapToCategory(CategoryDTO categoryDTO);

    @InheritConfiguration
    CategoryDTO mapToCategoryDTO(Category category);

    @InheritConfiguration
    List<CategoryDTO> mapToCategoryDTOs(List<Category> category);

    @InheritConfiguration
    void mapUpdateToCategory(CategoryDTO categoryDTO, @MappingTarget Category category);
}

