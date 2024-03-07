package com.edmart.client.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class Prices implements Serializable{

    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private BigDecimal discountPrice;
}
