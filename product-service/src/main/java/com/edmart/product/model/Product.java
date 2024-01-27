package com.edmart.product.model;

import com.edmart.client.product.Measurements;
import com.edmart.client.product.Prices;
import com.edmart.client.product.ProductStatus;
import com.edmart.client.product.Units;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
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
