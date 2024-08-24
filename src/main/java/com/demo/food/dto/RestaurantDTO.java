package com.demo.food.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeShip;
    private Date openDate;
    List<CategoryDTO> categorys;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public List<CategoryDTO> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryDTO> categorys) {
        this.categorys = categorys;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isFreeShip() {
        return isFreeShip;
    }

    public void setFreeShip(boolean freeShip) {
        isFreeShip = freeShip;
    }
}
