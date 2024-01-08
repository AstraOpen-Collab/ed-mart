package com.edmart.product.utils;

import com.edmart.product.dto.PageInfoDTO;
import org.springframework.data.domain.Page;

public class Pagination {

    public static PageInfoDTO getPageInfoDTO(Page<?> page){
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setCurrentPage(page.getNumber());
        pageInfoDTO.setTotalPages(page.getTotalPages());
        pageInfoDTO.setTotalElements(page.getTotalElements());
        pageInfoDTO.setPageSize(page.getSize());
        pageInfoDTO.setLast(page.isLast());

        return pageInfoDTO;
    }
}