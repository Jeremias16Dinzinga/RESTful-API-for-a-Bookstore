package com.dinzinga.bookstoreApi.dtos;

import com.dinzinga.bookstoreApi.models.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String publisher;
    @NotBlank
    @Size(max = 13)
    private int isbnNumber;
    @NotBlank
    private List<Author> authors;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(int isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
