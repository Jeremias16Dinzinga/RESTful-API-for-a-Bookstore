package com.dinzinga.bookstoreApi.services;

import com.dinzinga.bookstoreApi.models.Book;
import com.dinzinga.bookstoreApi.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }
    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
