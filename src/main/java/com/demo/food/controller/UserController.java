package com.demo.food.controller;

import com.demo.food.service.UserService;
import com.demo.food.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }
}
