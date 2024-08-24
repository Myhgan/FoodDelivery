package com.demo.food.service.imp;

import com.demo.food.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    Boolean insertRestaurant(MultipartFile file,
                             String title,
                             String subtitle,
                             String description,
                             boolean isFreeship,
                             String address,
                             String openDate);
    List<RestaurantDTO> getHomePageRestaurant();
    RestaurantDTO getDetailRestaurant(int id);
}
