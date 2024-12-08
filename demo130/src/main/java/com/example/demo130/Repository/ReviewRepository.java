package com.example.demo130.Repository;

import com.example.demo130.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByCustomer_CustomerId(int customerId);
    List<Review>findByProduct_ProductId(int productId);
}
