package com.dinzinga.bookstoreApi.controllers;

import com.dinzinga.bookstoreApi.dtos.BookDto;
import com.dinzinga.bookstoreApi.models.Book;
import com.dinzinga.bookstoreApi.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BookController {
    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody @Valid BookDto bookDto){
        var book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        book.setPublitionDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id){
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") UUID id){
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found.");
        }
        bookService.delete(bookOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("book deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid BookDto bookDto){
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book not found.");
        }
        var book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        book.setPublisher(bookOptional.get().getPublisher());
        book.setAuthors(bookOptional.get().getAuthors());
        book.setIsbnNumber(bookOptional.get().getIsbnNumber());
        book.setTitle(bookOptional.get().getTitle());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(book));
    }
}
