package com.dinzinga.bookstoreApi.services;

import com.dinzinga.bookstoreApi.models.Author;
import com.dinzinga.bookstoreApi.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }
    @Transactional
    public void delete(Author author) {
        authorRepository.delete(author);
    }
}
