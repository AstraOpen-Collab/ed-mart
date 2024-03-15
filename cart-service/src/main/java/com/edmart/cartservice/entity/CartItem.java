package com.edmart.cartservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_cart_item")
public class CartItem extends BaseEntity{

    @Id
    @SequenceGenerator(
            name = "cart_item_id_sequence",
            sequenceName = "cart_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_id_sequence"
    )
    private Long cartItemId;

    private Long productId;

    private Long userId;

    private int quantity;

    private Double price;
}
