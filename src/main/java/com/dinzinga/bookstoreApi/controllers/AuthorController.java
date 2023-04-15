package com.dinzinga.bookstoreApi.controllers;

import com.dinzinga.bookstoreApi.dtos.AuthorDto;
import com.dinzinga.bookstoreApi.models.Author;
import com.dinzinga.bookstoreApi.services.AuthorService;
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
@RequestMapping("/Authors")
public class AuthorController {
    final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAuthor(@RequestBody @Valid AuthorDto authorDto){
        var author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(author));
    }
    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAuthor(@PathVariable(value = "id") UUID id){
        Optional<Author> authorOptional = authorService.findById(id);
        if (!authorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(authorOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") UUID id){
        Optional<Author> authorOptional = authorService.findById(id);
        if (!authorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found.");
        }
        authorService.delete(authorOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid AuthorDto authorDto){
        Optional<Author> authorOptional = authorService.findById(id);
        if (!authorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found.");
        }
        var author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        author.setName(authorOptional.get().getName());
        author.setBiography(authorOptional.get().getBiography());
        author.setBooks(authorOptional.get().getBooks());
        return ResponseEntity.status(HttpStatus.OK).body(authorService.save(author));
    }
}
