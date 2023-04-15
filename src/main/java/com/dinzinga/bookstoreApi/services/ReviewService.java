package com.dinzinga.bookstoreApi.services;

import com.dinzinga.bookstoreApi.models.Review;
import com.dinzinga.bookstoreApi.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {
    final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
    public Optional<Review> findById(UUID id) {
        return reviewRepository.findById(id);
    }
    @Transactional
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
