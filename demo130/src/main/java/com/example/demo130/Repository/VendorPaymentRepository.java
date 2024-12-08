package com.example.demo130.Repository;





import com.example.demo130.model.Vendor;
import com.example.demo130.model.VendorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorPaymentRepository extends JpaRepository<VendorPayment, Integer> {

    // Fetch all payments by vendor id
    List<VendorPayment> findByVendor_VendorId(int vendorId);
}
