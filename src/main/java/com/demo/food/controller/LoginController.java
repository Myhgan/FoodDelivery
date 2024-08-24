package com.demo.food.controller;

import com.demo.food.payload.ResponseData;
import com.demo.food.payload.request.SignupRequest;
import com.demo.food.service.imp.LoginServiceImp;
import com.demo.food.utils.JwtUtilsHelper;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;
    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        logger.trace("kiem tra trace log");
        logger.debug("kiem tra debug log");
        logger.info("kiem tra info log");
        logger.warn("kiem tra warn log");
        logger.error("kiem tra error log");
        /*=========sinh ra key=============*/
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String encrypted = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(encrypted);

        if(loginServiceImp.checkLogin(username,password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);

            responseData.isSuccess();

        }
        else{
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
