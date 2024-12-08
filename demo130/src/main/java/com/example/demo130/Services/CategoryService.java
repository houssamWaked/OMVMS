package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.CategoryRepository;
import com.example.demo130.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indicates that this class is a service component for the Spring context
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Constructor-based dependency injection to inject the CategoryRepository
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Adds a new category to the database.
     *
     * @param category The category object to be added
     * @return The saved category object
     */
    public Category addCategory(Category category) {
        // Saves the category to the database and returns the saved entity
        return categoryRepository.save(category);
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return A list of all categories
     */
    public List<Category> getAllCategories() {
        // Returns all categories in the database
        return categoryRepository.findAll();
    }

    /**
     * Fetches a category by its ID.
     *
     * @param categoryId The ID of the category to be retrieved
     * @return The category object
     * @throws ResourceNotFoundException If the category is not found
     */
    public Category getCategoryById(int categoryId) {
        // Finds the category by ID, throws exception if not found
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
    }

    /**
     * Updates the details of an existing category.
     *
     * @param categoryId The ID of the category to be updated
     * @param updatedCategory The updated category object
     * @return The updated category object
     * @throws ResourceNotFoundException If the category is not found
     */
    public Category updateCategory(int categoryId, Category updatedCategory) {
        // Retrieves the category by ID, throws exception if not found
        Category category = getCategoryById(categoryId);

        // Updates the category's name
        category.setCategoryName(updatedCategory.getCategoryName());

        // Saves and returns the updated category
        return categoryRepository.save(category);
    }


}
