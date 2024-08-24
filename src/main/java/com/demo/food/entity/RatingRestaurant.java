package com.demo.food.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ratingrestaurant")
public class RatingRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;
    @Column(name = "content")
    private String content;
    @Column(name = "rate_point")
    private double ratePoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(double ratePoint) {
        this.ratePoint = ratePoint;
    }
}
