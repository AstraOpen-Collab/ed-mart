package com.edmart.category.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subcategory")
public class SubCategory extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "subcategory_id_sequence",
            sequenceName = "subcategory_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subcategory_id_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
