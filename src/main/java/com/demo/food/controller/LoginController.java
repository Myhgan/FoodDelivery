package com.demo.food.controller;

import com.demo.food.payload.ResponseData;
import com.demo.food.payload.request.SignupRequest;
import com.demo.food.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();
        if(loginServiceImp.checkLogin(username,password)){
            responseData.setData(true);
        }
        else
            responseData.setData(false);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
