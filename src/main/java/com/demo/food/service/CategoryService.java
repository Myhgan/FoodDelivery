package com.demo.food.service;

import com.demo.food.dto.CategoryDTO;
import com.demo.food.dto.MenuDTO;
import com.demo.food.entity.Category;
import com.demo.food.entity.Food;
import com.demo.food.repository.CategoryRepository;
import com.demo.food.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> listCategoryDTOS = new ArrayList<>();

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

        return listCategoryDTOS;
    }
}
