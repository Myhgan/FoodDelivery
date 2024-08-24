package com.demo.food.controller;

import com.demo.food.payload.request.OrderRequest;
import com.demo.food.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;
    @PostMapping()
    public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest){

        return new ResponseEntity<>(orderServiceImp.insertOrder(orderRequest), HttpStatus.OK);
    }

}
