package com.edmart.inventoryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "tbl_inventory")
public class Inventory extends BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "inventory_id_sequence",
            sequenceName = "inventory_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_id_sequence"
    )
    private Long inventoryId;
    private Long productId;
    private Integer itemQuantity;

}
