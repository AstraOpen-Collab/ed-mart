package com.edmart.client.vendor;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        value = "vendor",
        path = "/api/v1/vendors"
)
public interface VendorClient {

    @GetMapping
    ResponseEntity<VendorResponse> getAllVendors(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "vendorId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    );

    @GetMapping("/{vendorId}")
    ResponseEntity<VendorRecord> getVendorById(@PathVariable("vendorId") Long vendorId);
}
