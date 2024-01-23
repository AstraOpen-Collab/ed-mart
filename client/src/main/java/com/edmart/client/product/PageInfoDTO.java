package com.edmart.client.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDTO implements Serializable {
    long totalElements;
    long totalPages;
    long currentPage;
    private boolean last;
    private long pageSize;
}