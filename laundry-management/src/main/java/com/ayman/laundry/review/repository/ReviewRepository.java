package com.ayman.laundry.review.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayman.laundry.review.entity.Review;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    // Get all reviews for customer
    List<Review> findByCustomerId(Long customerId);



    // Get review by order
    Optional<Review> findByOrderId(Long orderId);



    // Check if order already has review
    boolean existsByOrderId(Long orderId);


}