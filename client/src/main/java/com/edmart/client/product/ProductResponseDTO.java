package com.edmart.client.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    List<ProductDTO> productList = new ArrayList<>();
    PageInfoDTO pageInfoDTO;
}
