package com.edmart.vendorservice.repository;


import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.vendorservice.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByVendorNameIgnoreCase(String name) throws VendorNotFoundException;
}
