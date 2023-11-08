package com.edmart.category.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_sequence"
    )
    private Long categoryId;

    @Column
    private String categoryName;

    @Column
    private String categoryDescription;

    @Column
    private String categoryDesignation;

    @OneToMany(cascade = CascadeType.ALL)
    List<SubCategory> subCategories;
}
