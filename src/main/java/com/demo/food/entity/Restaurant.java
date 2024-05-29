package com.demo.food.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "is_freeship")
    private Boolean isFreeship;
    @Column(name = "address")
    private String address;
    @Column(name = "open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> listRatingRestaurants;
    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;
    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> listMenuRestaurants;
    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromos;

    public Set<Promo> getListPromos() {
        return listPromos;
    }

    public void setListPromos(Set<Promo> listPromos) {
        this.listPromos = listPromos;
    }

    public Set<MenuRestaurant> getListMenuRestaurants() {
        return listMenuRestaurants;
    }

    public void setListMenuRestaurants(Set<MenuRestaurant> listMenuRestaurants) {
        this.listMenuRestaurants = listMenuRestaurants;
    }

    public Set<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(Set<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public Set<RatingRestaurant> getListRatingRestaurants() {
        return listRatingRestaurants;
    }

    public void setListRatingRestaurants(Set<RatingRestaurant> listRatingRestaurants) {
        this.listRatingRestaurants = listRatingRestaurants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
