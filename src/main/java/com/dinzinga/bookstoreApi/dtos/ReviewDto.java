package com.dinzinga.bookstoreApi.dtos;

import com.dinzinga.bookstoreApi.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewDto {
    @NotBlank
    @Size(max = 5)
    private int rating;
    @NotBlank
    private String comment;
    @NotBlank
    private Book book;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
