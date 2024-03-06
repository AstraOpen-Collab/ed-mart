package com.edmart.discountservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_discount")
public class Discount extends  BaseEntity{

    @Id
    @SequenceGenerator(
            name = "discount_id_sequence",
            sequenceName = "discount_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "discount_id_sequence"
    )
    private Long discountId;

    private String discountName;
    private String discountCode;

    private Double percentage;
    private Long productId;
    private Long categoryId;

    @JsonProperty("dateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    private Integer durationHours;

}
