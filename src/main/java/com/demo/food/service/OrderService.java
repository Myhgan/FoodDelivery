package com.demo.food.service;

import com.demo.food.entity.*;
import com.demo.food.entity.keys.KeyOrderItem;
import com.demo.food.payload.request.OrderRequest;
import com.demo.food.repository.OrderItemRepository;
import com.demo.food.repository.OrderRepository;
import com.demo.food.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users users = new Users();
            users.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);

            orderRepository.save(orders);

            List<OrderItem> items = new ArrayList<>();
            for (int idFood : orderRequest.getFoodIds()) {
                Food food = new Food();
                food.setId(idFood);

                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(), idFood);
                orderItem.setKeyOrderItem(keyOrderItem);
                items.add(orderItem);
                return true;
            }
            orderItemRepository.saveAll(items);
        }catch (Exception e){
            System.out.println("Error insert order" + e.getMessage());
        }
        return false;
    }
}
