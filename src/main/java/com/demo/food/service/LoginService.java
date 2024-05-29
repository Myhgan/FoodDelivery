package com.demo.food.service;

import com.demo.food.dto.UserDTO;
import com.demo.food.entity.Roles;
import com.demo.food.entity.Users;
import com.demo.food.payload.request.SignupRequest;
import com.demo.food.repository.UserRepository;
import com.demo.food.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUserDTO(){
        List<Users> usersList =  userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(Users users: usersList){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setFullname(users.getFullname());
            userDTO.setPassword(users.getPassword());
            userDTOList.add(userDTO);
//            System.out.println(users.getFullname());
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<Users> userList = userRepository.findByUserNameAndPassword(username, password);
        return userList.size()>0;
    }

    @Override
    public boolean addUser(SignupRequest signupRequest) {
        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());
        Users users = new Users();
        users.setFullname(signupRequest.getFullname());
        users.setUserName(signupRequest.getEmail());
        users.setPassword(signupRequest.getPassword());
        users.setRoles(roles);

        try {
            userRepository.save(users);
            return true;
        }catch (Exception  e){
            return false;
        }
    }
}
