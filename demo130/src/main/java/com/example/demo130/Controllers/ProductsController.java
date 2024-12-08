package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.Product;
import com.example.demo130.Services.ProductService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductsController handles the CRUD operations for products in the system.
 * It provides endpoints to add, update, retrieve, and delete products.
 */
@RestController
@RequestMapping("/api/products")  // Base URL for product-related endpoints
public class ProductsController {

    private final ProductService productsService;

    // Constructor to inject ProductService
    @Autowired
    public ProductsController(ProductService productsService) {
        this.productsService = productsService;
    }

    /**
     * Endpoint to add a new product.
     * @param product the product object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product) {
        try {
            // Attempt to add the product using the ProductService
            Product savedProduct = productsService.addProduct(product);
            return ResponseEntity.status(201).body(new ApiResponse("Product created successfully", savedProduct));
        } catch (Exception e) {
            // Catch any exception and return a failure message
            return ResponseEntity.status(400).body(new ApiResponse("Invalid product data"));
        }
    }

    /**
     * Endpoint to apply a discount to a product by its ID.
     * @param productId the ID of the product.
     * @param discountPercentage the percentage discount to apply.
     * @return ResponseEntity with success or failure message.
     */
    @PutMapping("/discount/{product_id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable("product_id") int productId,
            @RequestParam("discount") int discountPercentage) {
        try {
            // Apply the discount to the product
            productsService.applyDiscount(productId, discountPercentage);

            // Return a success response
            ApiResponse response = new ApiResponse("Discount applied successfully!");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Return a bad request if the product is not found or invalid input
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Handle unexpected errors
            ApiResponse response = new ApiResponse("An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint to retrieve a product by its name.
     * @param productName the name of the product to retrieve.
     * @return ResponseEntity with the product details or an error message if not found.
     */
    @GetMapping("/name/{product_name}")
    public ResponseEntity<ApiResponse> getByProductName(@PathVariable("product_name") String productName) {
        Product product = productsService.SearchProduct(productName);
        if (product == null) {
            return ResponseEntity.status(204).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.ok(new ApiResponse("Product retrieved successfully", product));
    }

    /**
     * Endpoint to retrieve all products in the system.
     * @return ResponseEntity with a list of products or a message if no products are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productsService.getAllProducts();
        if (products == null || products.isEmpty()) {
            return ResponseEntity.status(204).body(new ApiResponse("No products found"));
        }
        return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
    }

    /**
     * Endpoint to retrieve a specific product by its ID.
     * @param productId the ID of the product to retrieve.
     * @return ResponseEntity with the product details or an error message if not found.
     */
    @GetMapping("/id/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable int productId) {
        try {
            Product product = productsService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product found", product));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found with ID: " + productId));
        }
    }

    /**
     * Endpoint to retrieve products by their category name.
     * @param categoryName the name of the category.
     * @return ResponseEntity with the list of products or an error message if no products are found in the category.
     */
    @GetMapping("Category/{categoryName}")
    public ResponseEntity<ApiResponse> getProductsByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productsService.getProductByCategoryName(categoryName);
        if (products == null || products.isEmpty()) {
            return ResponseEntity.status(204).body(new ApiResponse("No products found in this category"));
        }
        return ResponseEntity.ok(new ApiResponse("Products for category '" + categoryName + "' retrieved successfully", products));
    }

    /**
     * Endpoint to update an existing product by its ID.
     * @param productId the ID of the product to update.
     * @param product the updated product details.
     * @return ResponseEntity with the updated product details or an error message if not found.
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        try {
            Product updatedProduct = productsService.updateProduct(productId, product);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found with ID: " + productId));
        }
    }


}
