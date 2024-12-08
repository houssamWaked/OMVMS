package com.example.demo130.Controllers;

import com.example.demo130.model.ApiResponse;
import com.example.demo130.model.Review;
import com.example.demo130.Services.ReviewService;
import com.example.demo130.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ReviewController handles CRUD operations for product reviews.
 * It provides endpoints for adding, retrieving, updating, and deleting reviews.
 */
@RestController
@RequestMapping("/api/reviews")  // Base URL for review-related endpoints
public class ReviewController {

    private final ReviewService reviewService;

    // Constructor to inject ReviewService
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Endpoint to add a new review.
     * @param review the review object to be created.
     * @return ResponseEntity with success or failure message.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addReview(@RequestBody Review review) {
        try {
            // Attempt to add the review using the ReviewService
            Review addedReview = reviewService.addReview(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Review added successfully", addedReview));
        } catch (Exception e) {
            // Return failure message in case of an error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add review"));
        }
    }

    /**
     * Endpoint to retrieve all reviews in the system.
     * @return ResponseEntity with a list of reviews or a message if no reviews are found.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No reviews found"));
        }
        return ResponseEntity.ok(new ApiResponse("Reviews retrieved successfully", reviews));
    }

    /**
     * Endpoint to retrieve a review by its ID.
     * @param reviewId the ID of the review to retrieve.
     * @return ResponseEntity with the review details or an error message if not found.
     */
    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> getReviewById(@PathVariable int reviewId) {
        try {
            Review review = reviewService.getReviewById(reviewId);
            return ResponseEntity.ok(new ApiResponse("Review found", review));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Review not found with ID: " + reviewId));
        }
    }

    /**
     * Endpoint to retrieve the average rating for a product.
     * @param productId the ID of the product for which the average rating is to be retrieved.
     * @return ResponseEntity with the average rating or an error message if the retrieval fails.
     */
    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<ApiResponse> getAverageRatingByProduct(@PathVariable int productId) {
        try {
            double averageRating = reviewService.getAverageRatingByProduct(productId);
            return ResponseEntity.ok(new ApiResponse("Average rating for product found", averageRating));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to retrieve average rating"));
        }
    }

    /**
     * Endpoint to retrieve reviews by a customer's ID.
     * @param customerId the ID of the customer for whom reviews are to be retrieved.
     * @return ResponseEntity with a list of reviews or a message if no reviews are found for the customer.
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse> getReviewsByCustomerId(@PathVariable int customerId) {
        List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No reviews found for customer ID: " + customerId));
        }
        return ResponseEntity.ok(new ApiResponse("Reviews found for customer", reviews));
    }

    /**
     * Endpoint to retrieve reviews by a product's ID.
     * @param productId the ID of the product for which reviews are to be retrieved.
     * @return ResponseEntity with a list of reviews or a message if no reviews are found for the product.
     */
    @GetMapping("/product/{product_id}")
    public ResponseEntity<ApiResponse> getReviewsByProductId(@PathVariable int product_id) {
        List<Review> reviews = reviewService.getReviewsByProductId(product_id);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No reviews found for product ID: " + product_id));
        }
        return ResponseEntity.ok(new ApiResponse("Reviews found for product", reviews));
    }

    /**
     * Endpoint to update an existing review.
     * @param reviewId the ID of the review to update.
     * @param review the updated review details.
     * @return ResponseEntity with the updated review details or an error message if not found.
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(@PathVariable int reviewId, @RequestBody Review review) {
        try {
            Review updatedReview = reviewService.updateReview(reviewId, review);
            return ResponseEntity.ok(new ApiResponse("Review updated successfully", updatedReview));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Review not found with ID: " + reviewId));
        }
    }
}
