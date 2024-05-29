package com.demo.food.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_cate")
    private String nameCate;
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> listFood;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> listMenuRestaurants;

    public Set<MenuRestaurant> getListMenuRestaurants() {
        return listMenuRestaurants;
    }

    public void setListMenuRestaurants(Set<MenuRestaurant> listMenuRestaurants) {
        this.listMenuRestaurants = listMenuRestaurants;
    }

    public Set<Food> getListFood() {
        return listFood;
    }

    public void setListFood(Set<Food> listFood) {
        this.listFood = listFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
