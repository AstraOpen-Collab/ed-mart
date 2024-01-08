package com.edmart.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDTO {
    long totalElements;
    long totalPages;
    long currentPage;
    private boolean last;
    private long pageSize;
}