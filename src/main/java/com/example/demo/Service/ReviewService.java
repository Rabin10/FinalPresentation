package com.example.demo.Service;

import com.example.demo.Entity.Review;
import com.example.demo.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByServiceId(int serviceId) {
        return reviewRepository.findByServiceServiceId(serviceId);
    }

    public Review getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReviewById(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public long getTotalReviews() {
        return reviewRepository.count();
    }
}
