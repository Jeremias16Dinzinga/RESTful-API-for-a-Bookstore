package com.dinzinga.bookstoreApi.dtos;

import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
    @NotBlank
    private String name;
    @NotBlank
    private String biography;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
