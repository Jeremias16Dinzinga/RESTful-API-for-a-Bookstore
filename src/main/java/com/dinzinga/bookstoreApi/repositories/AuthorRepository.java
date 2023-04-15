package com.dinzinga.bookstoreApi.repositories;

import com.dinzinga.bookstoreApi.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
