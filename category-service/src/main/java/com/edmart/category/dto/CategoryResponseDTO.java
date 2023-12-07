package com.edmart.category.dto;

import com.edmart.category.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponseDTO {
    List<Category> categoryList = new ArrayList<>();
    PageInfoDTO pageInfoDTO;
}
