package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.model.*;
import com.example.demo130.Repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indicates that this class is a service component for the Spring context
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor-based dependency injection to inject the CustomerRepository
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds a new customer to the database.
     *
     * @param name    Name of the customer
     * @param email   Email of the customer
     * @param password Password of the customer
     * @param phone   Phone number of the customer
     * @param address Address of the customer
     * @return The saved customer object
     */
    public Customer addCustomer(String name, String email, String password, String phone, String address) {
        // Creates a new Customer object and saves it to the database
        Customer customer = new Customer(name, email, password, phone, address);
        return customerRepository.save(customer);  // Save and return the customer
    }

    /**
     * Fetches a customer by their ID.
     *
     * @param customerId The ID of the customer to be retrieved
     * @return The customer object
     * @throws ResourceNotFoundException If the customer is not found
     */
    public Customer getCustomerById(int customerId) {
        // Finds the customer by ID, throws exception if not found
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    }

    /**
     * Fetches all customers in the database.
     *
     * @return A list of all customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();  // Return all customers
    }

    /**
     * Updates the details of an existing customer.
     *
     * @param customerId The ID of the customer to be updated
     * @param name       The updated name of the customer
     * @param email      The updated email of the customer
     * @param password   The updated password of the customer
     * @param phone      The updated phone number of the customer
     * @param address    The updated address of the customer
     * @return The updated customer object
     * @throws ResourceNotFoundException If the customer is not found
     */
    public Customer updateCustomer(int customerId, String name, String email, String password, String phone, String address) {
        // Retrieves the customer by ID, throws exception if not found
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        // Updates the customer's information
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhone(phone);
        customer.setAddress(address);

        // Saves and returns the updated customer
        return customerRepository.save(customer);
    }

    /**
     * Authenticates a customer based on email and password.
     *
     * @param email    The email of the customer
     * @param password The password of the customer
     * @return true if the customer exists and the password matches, otherwise false
     */
    public boolean AuthenticateCustomer(String email, String password) {
        // Retrieves the customer by email
        Customer customer = customerRepository.findByEmail(email).orElse(null);

        // Checks if the customer exists and if the password matches
        return customer != null && customer.getPassword().equals(password);
    }
}
