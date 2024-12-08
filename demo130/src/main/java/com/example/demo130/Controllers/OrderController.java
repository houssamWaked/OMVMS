package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.Orders;
import com.example.demo130.Services.OrderService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderController handles the CRUD operations for orders in the system.
 * It provides endpoints to add, update, retrieve, and delete orders.
 */
@RestController
@RequestMapping("/api/orders") // Base URL for order-related endpoints
public class OrderController {

    private final OrderService orderService;

    // Constructor to inject OrderService
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint to add a new order.
     * @param order the order object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody Orders order) {
        try {
            // Attempt to add the order using the OrderService
            Orders addedOrder = orderService.addOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Order created successfully", addedOrder));
        } catch (ResourceNotFoundException e) {
            // Handle the case where the order could not be created due to a resource issue
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to create order: " + e.getMessage()));
        } catch (Exception e) {
            // Catch any other general exception and return a failure message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to create order: " + e.getMessage()));
        }
    }

    /**
     * Endpoint to retrieve all orders.
     * @return ResponseEntity with a list of orders or a message if no orders are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            // Return a 204 No Content response if no orders exist
            return ResponseEntity.status(204).body(new ApiResponse("No orders found"));
        }
        // Return a 200 OK response with the list of orders
        return ResponseEntity.ok(new ApiResponse("Orders retrieved successfully", orders));
    }

    /**
     * Endpoint to retrieve a specific order by its ID.
     * @param orderId the ID of the order to retrieve.
     * @return ResponseEntity with the order details or an error message if not found.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable int orderId) {
        try {
            // Attempt to retrieve the order by ID
            Orders order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(new ApiResponse("Order found", order));
        } catch (ResourceNotFoundException ex) {
            // Handle case when the order is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Order not found with ID: " + orderId));
        }
    }

    /**
     * Endpoint to update an existing order by its ID.
     * @param orderId the ID of the order to update.
     * @param orders the updated order object.
     * @return ResponseEntity with the updated order details or an error message if not found.
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable int orderId, @RequestBody Orders orders) {
        try {
            // Attempt to update the order by ID
            Orders updatedOrder = orderService.updateOrder(orderId, orders);
            return ResponseEntity.ok(new ApiResponse("Order updated successfully", updatedOrder));
        } catch (ResourceNotFoundException ex) {
            // Handle case when the order to update is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Order not found with ID: " + orderId));
        }
    }


}
