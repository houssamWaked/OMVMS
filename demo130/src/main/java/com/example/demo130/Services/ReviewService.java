package com.example.demo130.Services;

import com.example.demo130.Exception.ResourceNotFoundException;
import com.example.demo130.Repository.ReviewRepository;
import com.example.demo130.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // Constructor-based dependency injection for ReviewRepository
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Adds a new review to the database.
     *
     * @param review the review to be added
     * @return the saved review
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review); // Save the review and return the saved review
    }

    /**
     * Retrieves all reviews from the database.
     *
     * @return a list of all reviews
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll(); // Retrieve and return all reviews from the database
    }

    /**
     * Retrieves a specific review by its ID.
     *
     * @param reviewId the ID of the review to be retrieved
     * @return the review with the specified ID
     * @throws ResourceNotFoundException if the review with the specified ID does not exist
     */
    public Review getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId)); // If not found, throw an exception
    }

    /**
     * Retrieves all reviews made by a specific customer.
     *
     * @param customerId the ID of the customer whose reviews are to be retrieved
     * @return a list of reviews made by the customer
     */
    public List<Review> getReviewsByCustomerId(int customerId) {
        return reviewRepository.findByCustomer_CustomerId(customerId); // Retrieve and return reviews by the specified customer
    }

    /**
     * Retrieves all reviews for a specific product.
     *
     * @param productId the ID of the product whose reviews are to be retrieved
     * @return a list of reviews for the product
     */
    public List<Review> getReviewsByProductId(int productId) {
        return reviewRepository.findByProduct_ProductId(productId); // Retrieve and return reviews for the specified product
    }

    /**
     * Calculates the average rating for a specific product.
     *
     * @param productId the ID of the product for which to calculate the average rating
     * @return the average rating of the product
     */
    public double getAverageRatingByProduct(int productId) {
        List<Review> reviews = reviewRepository.findByProduct_ProductId(productId);

        if (reviews.isEmpty()) {
            return 0.0; // Return 0 if there are no reviews for the product
        }

        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating(); // Sum up all the ratings
        }

        return totalRating / reviews.size(); // Calculate and return the average rating
    }

    /**
     * Updates an existing review with new details.
     *
     * @param reviewId      the ID of the review to be updated
     * @param updatedReview the updated review details
     * @return the updated review
     * @throws ResourceNotFoundException if the review with the specified ID does not exist
     */
    public Review updateReview(int reviewId, Review updatedReview) {
        Review review = getReviewById(reviewId); // Retrieve the review by ID
        // Update the review with the new details
        review.setRating(updatedReview.getRating());
        review.setComment(updatedReview.getComment());
        review.setReviewDate(updatedReview.getReviewDate());
        review.setProduct(updatedReview.getProduct());
        review.setCustomer(updatedReview.getCustomer());
        return reviewRepository.save(review); // Save and return the updated review
    }


}
