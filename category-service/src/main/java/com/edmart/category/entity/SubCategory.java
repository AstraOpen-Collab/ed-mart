package com.edmart.category.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonSerialize(using = ToStringSerializer.class)
@Table(name = "subcategory")
public class SubCategory extends BaseEntity implements Serializable {

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
    private Long subCategoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "categoryId"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

}
