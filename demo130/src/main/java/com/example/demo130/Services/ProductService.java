package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.ProductRepository;
import com.example.demo130.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor-based dependency injection for ProductRepository
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Adds a new product to the database.
     *
     * @param product the product to be added
     * @return the saved product
     */
    public Product addProduct(Product product) {
        return productRepository.save(product); // Save the product to the database and return the saved product
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Retrieve and return a list of all products from the database
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId the ID of the product to be retrieved
     * @return the product with the specified ID
     * @throws ResourceNotFoundException if the product is not found
     */
    public Product getProductById(int productId) {
        // Check if the product exists; if not, throw a ResourceNotFoundException
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }

    /**
     * Searches for a product by its name.
     *
     * @param productName the name of the product to search for
     * @return the product with the specified name, or null if no product is found
     */
    public Product SearchProduct(String productName) {
        return productRepository.findByProductName(productName); // Search for and return the product by name
    }

    /**
     * Updates the details of an existing product.
     *
     * @param productId       the ID of the product to be updated
     * @param updatedProduct the updated product details
     * @return the updated product
     * @throws ResourceNotFoundException if the product with the specified ID does not exist
     */
    public Product updateProduct(int productId, Product updatedProduct) {
        // Retrieve the existing product by its ID
        Product product = getProductById(productId);
        // Update the product details
        product.setProductName(updatedProduct.getProductName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setDescription(updatedProduct.getDescription());
        product.setVendor(updatedProduct.getVendor());
        product.setCategory(updatedProduct.getCategory());
        // Save and return the updated product
        return productRepository.save(product);
    }



    /**
     * Retrieves a list of products that belong to a specific category.
     *
     * @param categoryName the name of the category
     * @return a list of products in the specified category
     */
    public List<Product> getProductByCategoryName(String categoryName) {
        return productRepository.findByCategory_CategoryName(categoryName); // Return products from the specified category
    }

    /**
     * Applies a discount to a product by reducing its price.
     *
     * @param productId        the ID of the product to apply the discount to
     * @param discountPercentage the percentage of discount to be applied
     */
    public void applyDiscount(int productId, int discountPercentage) {
        // Fetch the product by its ID
        Product product = getProductById(productId);
        // Calculate the discounted price
        double discountedPrice = product.getPrice() - (product.getPrice() * discountPercentage / 100.0);
        // Update the product's price with the discounted value
        product.setPrice(discountedPrice);
        // Save the updated product with the new price
        productRepository.save(product);
    }
}
