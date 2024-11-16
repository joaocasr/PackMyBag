package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UploadProfileImageDto implements Serializable {
    private String username;
    private MultipartFile profile_image;

    public UploadProfileImageDto() {
    }

    public UploadProfileImageDto(String username, MultipartFile profile_image) {
        this.username = username;
        this.profile_image = profile_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(MultipartFile profile_image) {
        this.profile_image = profile_image;
    }
}
