package com.demo.food.controller;

import com.demo.food.payload.ResponseData;
import com.demo.food.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getHomeCategory(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
