package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.VendorPayment;
import com.example.demo130.Services.VendorPaymentService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VendorPaymentController handles CRUD operations for vendor payments.
 * It provides endpoints to add, update, retrieve, and calculate the total payments for a vendor.
 */
@RestController
@RequestMapping("/api/vendor-payments")  // Base URL for vendor payment-related endpoints
public class VendorPaymentController {

    private final VendorPaymentService vendorPaymentService;

    // Constructor to inject VendorPaymentService
    @Autowired
    public VendorPaymentController(VendorPaymentService vendorPaymentService) {
        this.vendorPaymentService = vendorPaymentService;
    }

    /**
     * Endpoint to add a new vendor payment.
     * @param vendorPayment the vendor payment object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addVendorPayment(@RequestBody VendorPayment vendorPayment) {
        try {
            // Attempt to add the vendor payment using the VendorPaymentService
            VendorPayment addedPayment = vendorPaymentService.addVendorPayment(vendorPayment);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Vendor payment added successfully", addedPayment));
        } catch (Exception e) {
            // Return failure message in case of an error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add vendor payment"));
        }
    }

    /**
     * Endpoint to retrieve all vendor payments.
     * @return ResponseEntity with a list of vendor payments or a message if no payments are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllVendorPayments() {
        List<VendorPayment> payments = vendorPaymentService.getAllVendorPayments();
        if (payments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No vendor payments found"));
        }
        return ResponseEntity.ok(new ApiResponse("Vendor payments retrieved successfully", payments));
    }

    /**
     * Endpoint to retrieve a vendor payment by its ID.
     * @param paymentId the ID of the vendor payment to retrieve.
     * @return ResponseEntity with the vendor payment details or an error message if not found.
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> getVendorPaymentById(@PathVariable int paymentId) {
        try {
            VendorPayment payment = vendorPaymentService.getVendorPaymentById(paymentId);
            return ResponseEntity.ok(new ApiResponse("Vendor payment found", payment));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vendor payment not found with ID: " + paymentId));
        }
    }

    /**
     * Endpoint to retrieve the total payment for a specific vendor by vendor ID.
     * @param vendorId the ID of the vendor whose payments are to be calculated.
     * @return ResponseEntity with the total payment amount or an error message if the vendor is not found.
     */
    @GetMapping("/vendors/{vendorId}")
    public ResponseEntity<ApiResponse> getVendorPaymentByVendorId(@PathVariable int vendorId) {
        try {
            // Call the service method to get the total payments for the vendor
            double totalPayment = vendorPaymentService.getTotalVendorPaymentByVendor(vendorId);

            // Return the total payment in the response body
            Map<String, Object> response = new HashMap<>();
            response.put("vendorId", vendorId);
            response.put("totalPayment", totalPayment);

            return ResponseEntity.ok(new ApiResponse("Vendor payment total retrieved successfully", response));
        } catch (ResourceNotFoundException ex) {
            // If the vendor is not found or any other exception occurs
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vendor not found with ID: " + vendorId));
        }
    }

    /**
     * Endpoint to update an existing vendor payment.
     * @param paymentId the ID of the payment to update.
     * @param vendorPayment the updated payment details.
     * @return ResponseEntity with the updated payment details or an error message if not found.
     */
    @PutMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> updateVendorPayment(@PathVariable int paymentId, @RequestBody VendorPayment vendorPayment) {
        try {
            VendorPayment updatedPayment = vendorPaymentService.updateVendorPayment(paymentId, vendorPayment);
            return ResponseEntity.ok(new ApiResponse("Vendor payment updated successfully", updatedPayment));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vendor payment not found with ID: " + paymentId));
        }
    }
}
