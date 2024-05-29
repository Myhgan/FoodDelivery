package com.demo.food.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;
    @Column(name = "create_date")
    private Date createDate;
    @OneToMany(mappedBy = "orders")
    private Set<OrderItem> listOrderItems;

    public Set<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(Set<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
