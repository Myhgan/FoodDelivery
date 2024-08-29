package com.demo.food.service;

import com.demo.food.dto.CategoryDTO;
import com.demo.food.dto.MenuDTO;
import com.demo.food.entity.Category;
import com.demo.food.entity.Food;
import com.demo.food.repository.CategoryRepository;
import com.demo.food.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new Gson();
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id"));
        String dataRedis = (String) redisTemplate.opsForValue().get("category");
        List<CategoryDTO> listCategoryDTOS = new ArrayList<>();
        if(dataRedis == null){
            Page<Category> listCategory = categoryRepository.findAll(pageRequest);

            for (Category data : listCategory) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(data.getNameCate());

                List<MenuDTO> menuDTOS = new ArrayList<>();
                for (Food dataFood : data.getListFood()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setTitle((dataFood.getTitle()));
                    menuDTO.setFreeship(dataFood.getFreeship());
                    menuDTO.setImage(dataFood.getImage());
                    menuDTOS.add(menuDTO);

                }
                categoryDTO.setMenus(menuDTOS);
                listCategoryDTOS.add(categoryDTO);
            }
            String dataJson = gson.toJson(listCategoryDTOS);
            redisTemplate.opsForValue().set("category", dataJson);

        }else {
            Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
            listCategoryDTOS = gson.fromJson(dataRedis, listType);
        }

        return listCategoryDTOS;
    }
}
