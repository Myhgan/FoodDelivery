package com.demo.food.service.imp;

import com.demo.food.dto.UserDTO;
import com.demo.food.payload.request.SignupRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUserDTO();
    boolean checkLogin(String username, String password);
    boolean addUser(SignupRequest signupRequest);
}
