package com.edmart.elasticsearch.model;

import com.edmart.client.product.Measurements;
import com.edmart.client.product.Prices;
import com.edmart.client.product.ProductStatus;
import com.edmart.client.product.Units;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "product", createIndex = false)
public class Product extends BaseEntity implements Serializable {

    @Id
    private Long productId;


    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long SKU;

    @Column
    private Long categoryId;

    @Embedded
    private Prices prices;

    @Embedded
    private Units units;

    private Integer quantity;

    @Embedded
    private Measurements measurements;

    @Lob
    private byte[] image;

    private Integer rating;

    private Long vendorId;

    private ProductStatus status;
}
