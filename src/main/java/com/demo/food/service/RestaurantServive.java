package com.demo.food.service;

import com.demo.food.dto.CategoryDTO;
import com.demo.food.dto.MenuDTO;
import com.demo.food.dto.RestaurantDTO;
import com.demo.food.entity.Food;
import com.demo.food.entity.MenuRestaurant;
import com.demo.food.entity.RatingRestaurant;
import com.demo.food.entity.Restaurant;
import com.demo.food.repository.RestaurantRepository;
import com.demo.food.service.imp.FileServiceImp;
import com.demo.food.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantServive implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public Boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship, String address, String openDate) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.savefile(file);
            if(isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(isFreeship);
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(date);
                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error insert restaurant " +e.getMessage());
        }

        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        for (Restaurant data : listData) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setFreeShip(data.getFreeship());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurants()));

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    private double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoint = 0;
        for (RatingRestaurant data: listRating) {
             totalPoint += data.getRatePoint();
        }
        return  totalPoint/ listRating.size();
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(restaurant.isPresent()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            Restaurant data = restaurant.get();

            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurants()));
            restaurantDTO.setFreeShip(data.getFreeship());
            restaurantDTO.setOpenDate(data.getOpenDate());

            //category
            for (MenuRestaurant menuRestaurant: data.getListMenuRestaurants()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                List<MenuDTO> menuDTOList = new ArrayList<>();

                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());
                for (Food food: menuRestaurant.getCategory().getListFood()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeship(food.getFreeship());
                    menuDTO.setTitle(food.getTitle());

                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategorys(categoryDTOList);

        }
        else {
            System.out.println("Hậu bị khùng");
        }
        return restaurantDTO;
    }
}
