package com.example.catalogService.dto;

public class InsertReviewDTO {
    private String username;
    private String name;
    private String profileImg;
    private String texto;
    private String timestamp;
    private int rating;

    public InsertReviewDTO() {
    }

    public InsertReviewDTO(String username, String name, String profileImg, String texto, String timestamp, int rating) {
        this.username = username;
        this.name = name;
        this.profileImg = profileImg;
        this.texto = texto;
        this.timestamp = timestamp;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
