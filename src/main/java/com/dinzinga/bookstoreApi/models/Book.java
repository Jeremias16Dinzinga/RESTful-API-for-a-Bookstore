package com.dinzinga.bookstoreApi.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Tb_Book")
public class Book implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String publisher;
    @Column(nullable = false)
    private LocalDateTime publitionDate;
    @Column(nullable = false, unique = true, length = 13)
    private int isbnNumber;
    @OneToMany
    private List<Review> reviews;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id",nullable = true)
    )
    private List<Author> authors;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getPublitionDate() {
        return publitionDate;
    }

    public void setPublitionDate(LocalDateTime publitionDate) {
        this.publitionDate = publitionDate;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
