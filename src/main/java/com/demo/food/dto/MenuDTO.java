package com.demo.food.dto;

public class MenuDTO {
    private String title;
    private String image;
    private  Boolean isFreeship;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getFreeship() {
        return isFreeship;
    }

    public void setFreeship(Boolean freeship) {
        isFreeship = freeship;
    }
}
