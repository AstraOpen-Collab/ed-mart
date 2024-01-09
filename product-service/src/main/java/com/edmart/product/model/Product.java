package com.edmart.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
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

    @Column
    private  Long inventoryId;

    @Column
    private Double price;

    @Column
    private Double oldPrice;

    @Column
    private Double newPrice;

    @Lob
    private byte[] image;
}
