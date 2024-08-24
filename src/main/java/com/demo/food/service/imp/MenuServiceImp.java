package com.demo.food.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    boolean createMenu(MultipartFile file,
                       String title,
                       boolean isFreeship,
                       String timeShip,
                       Double price,
                       int cateID);
}
