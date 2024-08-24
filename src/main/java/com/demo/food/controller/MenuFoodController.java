package com.demo.food.controller;

import com.demo.food.payload.ResponseData;
import com.demo.food.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/menu")
public class MenuFoodController {
    @Autowired
    MenuServiceImp menuServiceImp;
    @PostMapping("")
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam Boolean isFreeship,
            @RequestParam String timeShip,
            @RequestParam Double price,
            @RequestParam int cateID){
        ResponseData responseData = new ResponseData();
        responseData.setData(menuServiceImp.createMenu(file, title, isFreeship,
               timeShip, Double.valueOf(price), cateID));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
