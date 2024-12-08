package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.VendorRepository;
import com.example.demo130.Repository.ProductRepository;
import com.example.demo130.model.Product;
import com.example.demo130.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;

    // Constructor-based dependency injection for VendorRepository and ProductRepository
    @Autowired
    public VendorService(VendorRepository vendorRepository, ProductRepository productRepository) {
        this.vendorRepository = vendorRepository;
        this.productRepository = productRepository;
    }

    /**
     * Adds a new vendor to the database.
     *
     * @param vendor the vendor to be added
     * @return the saved vendor
     */
    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor); // Save and return the new vendor
    }

    /**
     * Retrieves all vendors from the database.
     *
     * @return a list of all vendors
     */
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll(); // Retrieve and return all vendors from the database
    }

    /**
     * Retrieves a specific vendor by its ID.
     *
     * @param vendorId the ID of the vendor to be retrieved
     * @return the vendor with the specified ID
     * @throws ResourceNotFoundException if the vendor with the specified ID does not exist
     */
    public Vendor getVendorById(int vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + vendorId)); // If not found, throw exception
    }

    /**
     * Updates an existing vendor's details.
     *
     * @param vendorId the ID of the vendor to be updated
     * @param updatedVendor the updated vendor details
     * @return the updated vendor
     * @throws ResourceNotFoundException if the vendor with the specified ID does not exist
     */
    public Vendor updateVendor(int vendorId, Vendor updatedVendor) {
        Vendor vendor = getVendorById(vendorId); // Retrieve the vendor by ID
        // Update the vendor with new values
        vendor.setName(updatedVendor.getName());
        vendor.setEmail(updatedVendor.getEmail());
        vendor.setPassword(updatedVendor.getPassword());
        vendor.setAddress(updatedVendor.getAddress());
        vendor.setRegistrationDate(updatedVendor.getRegistrationDate());
        return vendorRepository.save(vendor); // Save and return the updated vendor
    }



    /**
     * Retrieves all products associated with a specific vendor.
     *
     * @param vendorId the ID of the vendor whose products are to be retrieved
     * @return a list of products for the specified vendor
     * @throws ResourceNotFoundException if the vendor with the specified ID does not exist
     */
    public List<Product> getVendorProducts(int vendorId) {
        getVendorById(vendorId); // Ensure the vendor exists by calling getVendorById
        return productRepository.findByVendor_VendorId(vendorId); // Return the list of products associated with the vendor
    }

    /**
     * Authenticates a vendor using their email and password.
     *
     * @param email the email of the vendor
     * @param password the password of the vendor
     * @return true if the email and password match a vendor, false otherwise
     */
    public boolean AuthenticateVendor(String email, String password) {
        Vendor vendor = vendorRepository.findByEmail(email)
                .orElse(null); // Retrieve vendor by email, or null if not found

        return vendor != null && vendor.getPassword().equals(password); // Return true if password matches
    }
}
