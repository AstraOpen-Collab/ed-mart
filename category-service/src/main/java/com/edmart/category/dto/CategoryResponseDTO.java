package com.edmart.category.dto;

import com.edmart.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Slf4j
@ToString
public class CategoryResponseDTO implements Serializable{
    List<Category> categoryList = new ArrayList<>();
    PageInfoDTO pageInfoDTO;
}
