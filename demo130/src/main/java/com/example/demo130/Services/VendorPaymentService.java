package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.VendorPaymentRepository;
import com.example.demo130.model.VendorPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPaymentService {

    private final VendorPaymentRepository vendorPaymentRepository;

    // Constructor-based dependency injection for VendorPaymentRepository
    @Autowired
    public VendorPaymentService(VendorPaymentRepository vendorPaymentRepository) {
        this.vendorPaymentRepository = vendorPaymentRepository;
    }

    /**
     * Adds a new vendor payment to the database.
     *
     * @param vendorPayment the vendor payment to be added
     * @return the saved vendor payment
     */
    public VendorPayment addVendorPayment(VendorPayment vendorPayment) {
        return vendorPaymentRepository.save(vendorPayment); // Save the vendor payment and return the saved object
    }

    /**
     * Retrieves all vendor payments from the database.
     *
     * @return a list of all vendor payments
     */
    public List<VendorPayment> getAllVendorPayments() {
        return vendorPaymentRepository.findAll(); // Retrieve and return all vendor payments from the database
    }

    /**
     * Retrieves a specific vendor payment by its ID.
     *
     * @param paymentId the ID of the vendor payment to be retrieved
     * @return the vendor payment with the specified ID
     * @throws ResourceNotFoundException if the vendor payment with the specified ID does not exist
     */
    public VendorPayment getVendorPaymentById(int paymentId) {
        return vendorPaymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor payment not found with ID: " + paymentId)); // If not found, throw exception
    }

    /**
     * Retrieves the total amount of payments made to a specific vendor.
     *
     * @param vendorId the ID of the vendor for whom the total payments are to be calculated
     * @return the total payment amount for the vendor
     */
    public double getTotalVendorPaymentByVendor(int vendorId) {
        List<VendorPayment> payments = vendorPaymentRepository.findByVendor_VendorId(vendorId);

        if (payments == null || payments.isEmpty()) {
            return 0; // Return 0 if no payments are found for the vendor
        }

        // Calculate the sum of all payment amounts
        return payments.stream()
                .mapToDouble(VendorPayment::getAmount) // Map each payment to its amount
                .sum(); // Return the total sum of all payments
    }

    /**
     * Updates an existing vendor payment.
     *
     * @param paymentId           the ID of the vendor payment to be updated
     * @param updatedVendorPayment the updated vendor payment details
     * @return the updated vendor payment
     * @throws ResourceNotFoundException if the vendor payment with the specified ID does not exist
     */
    public VendorPayment updateVendorPayment(int paymentId, VendorPayment updatedVendorPayment) {
        VendorPayment vendorPayment = getVendorPaymentById(paymentId); // Retrieve the vendor payment by ID
        // Update the vendor payment with new values
        vendorPayment.setAmount(updatedVendorPayment.getAmount());
        vendorPayment.setPaymentDate(updatedVendorPayment.getPaymentDate());
        vendorPayment.setVendor(updatedVendorPayment.getVendor());
        return vendorPaymentRepository.save(vendorPayment); // Save and return the updated vendor payment
    }


}
