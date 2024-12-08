package com.example.demo130.Controllers;

import com.example.demo130.model.*;
import com.example.demo130.Services.CustomerService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers") // Maps HTTP requests to the "/api/customers" endpoint
public class CustomerController {

    private final CustomerService customerService;

    // Constructor to inject the CustomerService dependency
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Adds a new customer.
     *
     * @param requestBody A map containing customer details
     * @return a response with status 201 if the customer is created successfully or 400 if there is an error
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody Map<String, String> requestBody) {
        // Extracting customer details from the request body
        String name = requestBody.get("name");
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        String phone = requestBody.get("phone");
        String address = requestBody.get("address");

        try {
            // Creating a new customer using the service
            Customer addedCustomer = customerService.addCustomer(name, email, password, phone, address);

            // Preparing the response with customer details
            Map<String, Object> response = new HashMap<>();
            response.put("id", addedCustomer.getCustomerId());
            response.put("name", addedCustomer.getName());
            response.put("email", addedCustomer.getEmail());
            response.put("phone", addedCustomer.getPhone());
            response.put("address", addedCustomer.getAddress());

            // Returning a successful response
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Customer created successfully", response));
        } catch (Exception e) {
            // Returning an error response in case of an exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid customer data"));
        }
    }

    /**
     * Retrieves all customers.
     *
     * @return a response with a list of customers or 204 if no customers are found
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers(); // Fetching customers from the service
        if (customers.isEmpty()) {
            // Returning 204 (No Content) if no customers are found
            return ResponseEntity.status(204).body(new ApiResponse("No customers found"));
        }
        // Returning the list of customers if found
        return ResponseEntity.ok(new ApiResponse("Customers retrieved successfully", customers));
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId the ID of the customer to be retrieved
     * @return a response with customer details or 404 if not found
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable int customerId) {
        try {
            // Fetching customer by ID using the service
            Customer customer = customerService.getCustomerById(customerId);
            Map<String, Object> response = new HashMap<>();
            response.put("id", customer.getCustomerId());
            response.put("name", customer.getName());
            response.put("email", customer.getEmail());
            response.put("phone", customer.getPhone());
            response.put("address", customer.getAddress());

            // Returning the customer details
            return ResponseEntity.ok(new ApiResponse("Customer found", response));
        } catch (ResourceNotFoundException ex) {
            // Returning error if customer is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Customer not found with ID: " + customerId));
        }
    }

    /**
     * Authenticates a customer by email and password.
     *
     * @param loginRequest the login credentials
     * @return a response indicating whether the authentication was successful or failed
     */
    @PostMapping("/log/In")
    public ResponseEntity<ApiResponse> authenticateCustomer(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = customerService.AuthenticateCustomer(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Returning success response if authentication is successful
            return ResponseEntity.ok(new ApiResponse("Authentication successful", true));
        } else {
            // Returning error response if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid email or password", false));
        }
    }

    /**
     * Updates an existing customer's information.
     *
     * @param id          the ID of the customer to be updated
     * @param requestBody A map containing the updated customer details
     * @return a response with the updated customer details or 404 if not found
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        // Extracting updated customer details from the request body
        String name = requestBody.get("name");
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        String phone = requestBody.get("phone");
        String address = requestBody.get("address");

        try {
            // Updating the customer using the service
            Customer updatedCustomer = customerService.updateCustomer(id, name, email, password, phone, address);

            // Preparing the response with updated customer details
            Map<String, Object> response = new HashMap<>();
            response.put("id", updatedCustomer.getCustomerId());
            response.put("name", updatedCustomer.getName());
            response.put("email", updatedCustomer.getEmail());
            response.put("phone", updatedCustomer.getPhone());
            response.put("address", updatedCustomer.getAddress());

            // Returning the updated customer details
            return ResponseEntity.ok(new ApiResponse("Customer updated successfully", response));
        } catch (ResourceNotFoundException ex) {
            // Returning error response if the customer is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Customer not found with ID: " + id));
        }
    }

}
