package com.dinzinga.bookstoreApi.controllers;

import com.dinzinga.bookstoreApi.dtos.ReviewDto;
import com.dinzinga.bookstoreApi.models.Review;
import com.dinzinga.bookstoreApi.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Review")
public class ReviewController {
    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Object> saveReview(@RequestBody @Valid ReviewDto reviewDto){
        var review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(review));
    }
    @GetMapping
    public ResponseEntity<Page<Review>> getAllReview(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id){
        Optional<Review> reviewOptional = reviewService.findById(id);
        if (!reviewOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviewOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable(value = "id") UUID id){
        Optional<Review> reviewOptional = reviewService.findById(id);
        if (!reviewOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("review not found.");
        }
        reviewService.delete(reviewOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid ReviewDto reviewDto){
        Optional<Review> reviewOptional = reviewService.findById(id);
        if (!reviewOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found.");
        }
        var review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        review.setComment(reviewOptional.get().getComment());
        review.setRating(reviewOptional.get().getRating());
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.save(review));
    }
}
