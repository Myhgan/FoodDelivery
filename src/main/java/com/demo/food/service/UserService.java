package com.demo.food.service;

import com.demo.food.dto.UserDTO;
import com.demo.food.entity.Users;
import com.demo.food.repository.UserRepository;
import com.demo.food.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUser() {
        List<Users> usersList =  userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(Users users: usersList){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setFullname(users.getFullname());
            userDTO.setPassword(users.getPassword());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
