package com.demo.food.service;

import com.demo.food.entity.Category;
import com.demo.food.entity.Food;
import com.demo.food.entity.Restaurant;
import com.demo.food.repository.FoodRepository;
import com.demo.food.service.imp.FileServiceImp;
import com.demo.food.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service

public class MenuService implements MenuServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    FoodRepository foodRepository;
    @Override
    public boolean createMenu(MultipartFile file, String title, boolean isFreeship, String timeShip, Double price, int cateID) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.savefile(file);
            if(isSaveFileSuccess) {
                Food food = new Food();
                food.setTitle(title);
                food.setFreeship(isFreeship);
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setImage(file.getOriginalFilename());

                Category category = new Category();
                category.setId(cateID);
                food.setCategory(category);

                foodRepository.save(food);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error insert restaurant " +e.getMessage());
        }

        return isInsertSuccess;
    }
}
