package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.OrderItemRepository;
import com.example.demo130.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indicates that this class is a service component in the Spring context
public class OrderItemService {

    private final OrderItemRepository orderItemsRepository;

    // Constructor-based dependency injection for OrderItemRepository
    @Autowired
    public OrderItemService(OrderItemRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    /**
     * Adds a new order item to the database.
     *
     * @param orderItem The order item to be added
     * @return The saved order item
     */
    public OrderItem addOrderItem(OrderItem orderItem) {
        // Saves the order item to the database and returns the saved entity
        return orderItemsRepository.save(orderItem);
    }

    /**
     * Retrieves all order items from the database.
     *
     * @return A list of all order items
     */
    public List<OrderItem> getAllOrderItems() {
        // Returns all order items in the database
        return orderItemsRepository.findAll();
    }

    /**
     * Fetches an order item by its ID.
     *
     * @param orderId The ID of the order item to be retrieved
     * @return The order item object
     * @throws ResourceNotFoundException If the order item is not found
     */
    public OrderItem getOrderItemById(int orderId) {
        // Finds the order item by ID, throws an exception if not found
        return orderItemsRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found with ID: " + orderId));
    }

    /**
     * Updates the details of an existing order item.
     *
     * @param orderId The ID of the order item to be updated
     * @param updatedOrderItem The updated order item details
     * @return The updated order item
     * @throws ResourceNotFoundException If the order item is not found
     */
    public OrderItem updateOrderItem(int orderId, OrderItem updatedOrderItem) {
        // Retrieves the order item by ID, throws exception if not found
        OrderItem orderItem = getOrderItemById(orderId);

        // Updates the order item's properties
        orderItem.setProduct(updatedOrderItem.getProduct());
        orderItem.setQuantity(updatedOrderItem.getQuantity());
        orderItem.setPrice(updatedOrderItem.getPrice());

        // Saves and returns the updated order item
        return orderItemsRepository.save(orderItem);
    }
}
