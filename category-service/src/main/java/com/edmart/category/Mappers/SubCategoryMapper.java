//package com.edmart.category.Mappers;
//
//import com.edmart.category.dto.SubCategoryDTO;
//import com.edmart.category.entity.SubCategory;
//import org.mapstruct.InheritConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.ReportingPolicy;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
//public interface SubCategoryMapper {
//
//    SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);
//
//    SubCategory mapToSubCategory(SubCategoryDTO subCategoryDTO);
//
//    @InheritConfiguration
//    SubCategoryDTO mapToSubCategoryDTO(SubCategory subCategory);
//
//    @InheritConfiguration
//    List<SubCategoryDTO> mapToSubCategoryDTOs(List<SubCategory> subCategories);
//
//    @InheritConfiguration
//    void mapUpdateToSubCategory(SubCategoryDTO subCategoryDTO, @MappingTarget SubCategory subCategory);
//}
//
