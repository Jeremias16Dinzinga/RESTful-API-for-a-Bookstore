package com.dinzinga.bookstoreApi.repositories;

import com.dinzinga.bookstoreApi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
