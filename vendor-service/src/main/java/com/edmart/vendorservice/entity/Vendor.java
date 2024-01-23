package com.edmart.vendorservice.entity;


import com.edmart.client.vendor.Address;
import com.edmart.client.vendor.Contact;
import com.edmart.client.vendor.PurchaseInformation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="tbl_vendor")
public class Vendor implements Serializable {

    @Id
    @SequenceGenerator(

            name = "vendor_id_sequence",
            sequenceName = "vendor_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vendor_id_sequence"
    )
    private Long vendorId;

    private String vendorName;
    @Embedded
    private Address vendorAddress;
    @Embedded
    private Contact vendorContact;
    @Embedded
    private PurchaseInformation purchaseInfos;

    @Lob
    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
