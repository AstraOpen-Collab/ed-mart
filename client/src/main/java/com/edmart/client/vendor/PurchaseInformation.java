package com.edmart.client.vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PurchaseInformation implements Serializable {

    private String paymentTerms;
    private String taxingScheme;
    private String carrier;
    private String currency;

}
