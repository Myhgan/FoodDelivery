package com.demo.food.controller;

import com.demo.food.payload.ResponseData;
import com.demo.food.service.imp.FileServiceImp;
import com.demo.food.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    RestaurantServiceImp restaurantServiceImp;
    @PostMapping("")
    public ResponseEntity<?> createRestaurant(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam String description,
            @RequestParam boolean isFreeship,
            @RequestParam String address,
            @RequestParam String openDate){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.insertRestaurant(file, title, subtitle,
                description, isFreeship, address, openDate));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getHomeRestaurant(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getHomePageRestaurant());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/file/{filename:.+}")
    public  ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
        Resource resource =fileServiceImp.loadFile(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment: filename\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getDetailRestaurant(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
