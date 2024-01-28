package com.edmart.vendorservice.service;

import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.vendor.VendorRecord;
import com.edmart.client.vendor.VendorResponse;

import java.util.Optional;

public interface VendorService {

    VendorResponse getAllVendors(int pageNo, int pageSize, String sortBy, String sortDir);

    VendorResponse getVendorsByPage(int pageNo, int pageSize, String sortBy, String sortDir);
    VendorRecord getVendorById(Long vendorId) throws VendorNotFoundException;

    Optional<VendorRecord> getVendorByName(String vendorName) throws VendorNotFoundException;

    void createNewVendor(VendorRecord vendorRecord) throws VendorNotFoundException;

    void deleteVendorById(Long vendorId) throws VendorNotFoundException;

    void updateVendor(Long vendorId, VendorRecord vendorRecord) throws VendorNotFoundException;

}
