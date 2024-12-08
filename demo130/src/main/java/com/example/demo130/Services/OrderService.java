package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.*;
import com.example.demo130.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Marks this class as a service component in Spring context
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    // Constructor-based dependency injection for all repositories and services
    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService,
                        CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    /**
     * Adds a new order to the database.
     *
     * @param order The order object to be added
     * @return The saved order
     */
    public Orders addOrder(Orders order) {
        // Log the incoming order for debugging purposes
        System.out.println("Incoming Order: " + order);

        // Fetch the customer and vendor entities from their respective repositories
        Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Vendor vendor = vendorRepository.findById(order.getVendor().getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        // Log the fetched customer and vendor entities for debugging
        System.out.println("Fetched Customer: " + customer);
        System.out.println("Fetched Vendor: " + vendor);

        // Set the fetched customer and vendor into the order
        order.setCustomer(customer);
        order.setVendor(vendor);

        // Save the order in the database and return the saved order
        Orders savedOrder = orderRepository.save(order);

        // Log the saved order for debugging
        System.out.println("Saved Order: " + savedOrder);

        return savedOrder;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of all orders
     */
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Fetches an order by its ID.
     *
     * @param orderId The ID of the order to be fetched
     * @return The order with the specified ID
     * @throws ResourceNotFoundException If the order is not found
     */
    public Orders getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found with ID: " + orderId));
    }

    /**
     * Updates the details of an existing order.
     *
     * @param orderId The ID of the order to be updated
     * @param updatedOrders The updated order details
     * @return The updated order
     */
    public Orders updateOrder(int orderId, Orders updatedOrders) {
        // Retrieve the existing order
        Orders orders = getOrderById(orderId);

        // Update the order's properties with the new values
        orders.setOrderDate(updatedOrders.getOrderDate());
        orders.setTotalPrice(updatedOrders.getTotalPrice());
        orders.setCustomer(updatedOrders.getCustomer());
        orders.setVendor(updatedOrders.getVendor());

        // Save and return the updated order
        return orderRepository.save(orders);
    }
}
