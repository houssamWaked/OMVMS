package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.LoginRequest;
import com.example.demo130.model.Product;
import com.example.demo130.model.Vendor;
import com.example.demo130.Services.VendorService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * VendorController handles CRUD operations for vendors.
 * It provides endpoints for adding, retrieving, updating, deleting vendors,
 * and authenticating vendors via login.
 */
@RestController
@RequestMapping("/api/vendors")  // Base URL for vendor-related endpoints
public class VendorController {

    private final VendorService vendorService;

    // Constructor to inject VendorService
    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    /**
     * Endpoint to add a new vendor.
     * @param vendor the vendor object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addVendor(@RequestBody Vendor vendor) {
        try {
            // Attempt to add the vendor using the VendorService
            Vendor addedVendor = vendorService.addVendor(vendor);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Vendor added successfully", addedVendor));
        } catch (Exception e) {
            // Return failure message in case of an error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add vendor"));
        }
    }

    /**
     * Endpoint to retrieve all vendors in the system.
     * @return ResponseEntity with a list of vendors or a message if no vendors are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        if (vendors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No vendors found"));
        }
        return ResponseEntity.ok(new ApiResponse("Vendors retrieved successfully", vendors));
    }

    /**
     * Endpoint to retrieve a vendor by its ID.
     * @param vendorId the ID of the vendor to retrieve.
     * @return ResponseEntity with the vendor details or an error message if not found.
     */
    @GetMapping("/{vendorId}")
    public ResponseEntity<ApiResponse> getVendorById(@PathVariable int vendorId) {
        try {
            Vendor vendor = vendorService.getVendorById(vendorId);
            return ResponseEntity.ok(new ApiResponse("Vendor found", vendor));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vendor not found with ID: " + vendorId));
        }
    }

    /**
     * Endpoint to authenticate a vendor using email and password.
     * @param loginRequest the login request containing email and password.
     * @return ResponseEntity with authentication result message.
     */
    @PostMapping("/log/In")
    public ResponseEntity<ApiResponse> authenticateVendor(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = vendorService.AuthenticateVendor(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok(new ApiResponse("Authentication successful", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid email or password", false));
        }
    }

    /**
     * Endpoint to retrieve all products for a specific vendor by vendor ID.
     * @param vendorId the ID of the vendor.
     * @return ResponseEntity with a list of products or a message if no products are found.
     */
    @GetMapping("products/{vendorId}")
    public ResponseEntity<ApiResponse> getVendorProductsByVendorId(@PathVariable int vendorId) {
        List<Product> products = vendorService.getVendorProducts(vendorId);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No products found for vendor ID: " + vendorId));
        }
        return ResponseEntity.ok(new ApiResponse("Products found for vendor", products));
    }

    /**
     * Endpoint to update an existing vendor's information.
     * @param vendorId the ID of the vendor to update.
     * @param vendor the updated vendor details.
     * @return ResponseEntity with the updated vendor details or an error message if not found.
     */
    @PutMapping("/{vendorId}")
    public ResponseEntity<ApiResponse> updateVendor(@PathVariable int vendorId, @RequestBody Vendor vendor) {
        try {
            Vendor updatedVendor = vendorService.updateVendor(vendorId, vendor);
            return ResponseEntity.ok(new ApiResponse("Vendor updated successfully", updatedVendor));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Vendor not found with ID: " + vendorId));
        }
    }


}
