package com.edmart.vendorservice.Dto;

import com.edmart.client.vendor.VendorRecord;
import com.edmart.vendorservice.entity.Vendor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.function.Function;

@Service
public class VendorDtoMapper implements Function<Vendor, VendorRecord>, Serializable {

    @Override
    public VendorRecord apply(Vendor vendor) {
        return new VendorRecord(
                vendor.getVendorId(),
                vendor.getVendorName(),
                vendor.getVendorAddress(),
                vendor.getVendorContact(),
                vendor.getPurchaseInfos(),
                vendor.getImage()
        );
    }
}
