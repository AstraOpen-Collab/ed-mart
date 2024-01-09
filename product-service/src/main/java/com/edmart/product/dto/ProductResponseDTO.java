package com.edmart.product.dto;

import com.edmart.product.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    List<Product> productList = new ArrayList<>();
    PageInfoDTO pageInfoDTO;
}
