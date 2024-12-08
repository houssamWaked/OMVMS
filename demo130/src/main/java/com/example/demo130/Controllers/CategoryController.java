package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.Category;
import com.example.demo130.Services.CategoryService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories") // Maps HTTP requests to this controller's methods
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor-based dependency injection for CategoryService
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Creates a new category.
     *
     * @param category the category data to be created
     * @return a response with status 201 (created) if successful, or 400 (bad request) if invalid data
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.addCategory(category); // Add the category to the database
            return ResponseEntity.status(201).body(new ApiResponse("Category created successfully", savedCategory)); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid category data")); // Return error response for invalid data
        }
    }

    /**
     * Retrieves all categories.
     *
     * @return a response with the list of categories, or 204 (no content) if no categories are found
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories(); // Get all categories from the service
        if (categories == null || categories.isEmpty()) {
            return ResponseEntity.status(204).body(new ApiResponse("No categories found")); // Return empty response if no categories
        }
        return ResponseEntity.ok(new ApiResponse("Categories retrieved successfully", categories)); // Return the list of categories
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId the ID of the category to be retrieved
     * @return a response with the category details, or 404 (not found) if the category doesn't exist
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable int categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId); // Get category by ID
            return ResponseEntity.ok(new ApiResponse("Category found", category)); // Return category details if found
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponse("Category not found with ID: " + categoryId)); // Return error if not found
        }
    }

    /**
     * Updates a category by its ID.
     *
     * @param categoryId the ID of the category to be updated
     * @param category the updated category data
     * @return a response with the updated category, or 404 (not found) if the category doesn't exist
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(categoryId, category); // Update the category
            return ResponseEntity.ok(new ApiResponse("Category updated successfully", updatedCategory)); // Return updated category
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(new ApiResponse("Category not found with ID: " + categoryId)); // Return error if not found
        }
    }

}
