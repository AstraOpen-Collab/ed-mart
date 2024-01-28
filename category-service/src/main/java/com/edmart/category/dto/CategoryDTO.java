package com.edmart.category.dto;

import com.edmart.category.entity.SubCategory;
import com.edmart.contracts.category.SubCategoryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

public record CategoryDTO(
        Long categoryId,
        String categoryName,
        String categoryDescription,
        String categoryDesignation,
        List<SubCategory> subCategories) implements Serializable {}