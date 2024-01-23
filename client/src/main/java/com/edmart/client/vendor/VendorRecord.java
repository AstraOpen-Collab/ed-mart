package com.edmart.client.vendor;


import java.io.Serializable;

public record VendorRecord(
        Long vendorId,
        String vendorName,
        Address vendorAddress,
        Contact vendorContact,
        PurchaseInformation purchaseInfos,

        String image
) implements Serializable {
}
