package com.demo.food.entity;

import com.demo.food.entity.keys.KeyOrderItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keyOrderItem;
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id", updatable = false, insertable = false)
    private Food food;

    @Column(name = "create_date")
    private Date createDate;
}
