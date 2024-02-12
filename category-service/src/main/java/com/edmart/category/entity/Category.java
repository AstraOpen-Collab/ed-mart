package com.edmart.category.entity;


import com.edmart.category.client.CategorySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "Category", description = "This class documents all the fields and data types involve in describing the Category entity")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "category")
//@JsonSerialize(using = CategorySerializer.class)
public class Category extends BaseEntity implements Serializable {

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
    @ApiModelProperty(dataType = "Long" , example = "123")
    private Long categoryId;

    @Column
    @ApiModelProperty(dataType = "String" , example = "name")
    private String categoryName;

    @Column
    @ApiModelProperty(dataType = "String" , example = "description")
    private String categoryDescription;

    @Column
    @ApiModelProperty(dataType = "String" , example = "designation")
    private String categoryDesignation;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    @ApiModelProperty(dataType = "Collection" , example = "List<Object>")
    private List<SubCategory> subCategories;

}
