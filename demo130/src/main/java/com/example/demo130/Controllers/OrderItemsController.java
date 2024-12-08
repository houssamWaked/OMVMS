package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.OrderItem;
import com.example.demo130.Services.OrderItemService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderItemsController handles the CRUD operations for order items in the system.
 * It provides endpoints to add, update, retrieve, and delete order items.
 */
@RestController
@RequestMapping("/api/order-items")  // Base URL for order-item related endpoints
public class OrderItemsController {

    private final OrderItemService orderItemsService;

    // Constructor to inject OrderItemService
    @Autowired
    public OrderItemsController(OrderItemService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    /**
     * Endpoint to add a new order item.
     * @param orderItem the order item object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addOrderItem(@RequestBody OrderItem orderItem) {
        try {
            // Attempt to add the order item using the OrderItemService
            OrderItem addedOrderItem = orderItemsService.addOrderItem(orderItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Order item created successfully", addedOrderItem));
        } catch (Exception e) {
            // Catch any exception and return a failure message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to create order item"));
        }
    }

    /**
     * Endpoint to retrieve all order items.
     * @return ResponseEntity with a list of order items or a message if no order items are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemsService.getAllOrderItems();
        if (orderItems.isEmpty()) {
            // Return a 204 No Content response if no order items exist
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No order items found"));
        }
        // Return a 200 OK response with the list of order items
        return ResponseEntity.ok(new ApiResponse("Order items retrieved successfully", orderItems));
    }

    /**
     * Endpoint to retrieve a specific order item by its ID.
     * @param orderItemId the ID of the order item to retrieve.
     * @return ResponseEntity with the order item details or an error message if not found.
     */
    @GetMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse> getOrderItemById(@PathVariable int orderItemId) {
        try {
            // Attempt to retrieve the order item by ID
            OrderItem orderItem = orderItemsService.getOrderItemById(orderItemId);
            return ResponseEntity.ok(new ApiResponse("Order item found", orderItem));
        } catch (ResourceNotFoundException ex) {
            // Handle case when the order item is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Order item not found with ID: " + orderItemId));
        }
    }

    /**
     * Endpoint to update an existing order item by its ID.
     * @param orderItemId the ID of the order item to update.
     * @param orderItem the updated order item object.
     * @return ResponseEntity with the updated order item details or an error message if not found.
     */
    @PutMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse> updateOrderItem(@PathVariable int orderItemId, @RequestBody OrderItem orderItem) {
        try {
            // Attempt to update the order item by ID
            OrderItem updatedOrderItem = orderItemsService.updateOrderItem(orderItemId, orderItem);
            return ResponseEntity.ok(new ApiResponse("Order item updated successfully", updatedOrderItem));
        } catch (ResourceNotFoundException ex) {
            // Handle case when the order item to update is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Order item not found with ID: " + orderItemId));
        }
    }

}
