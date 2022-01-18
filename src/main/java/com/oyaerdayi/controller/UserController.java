package com.oyaerdayi.controller;

import com.oyaerdayi.converter.UserConverter;
import com.oyaerdayi.dao.UserDao;
import com.oyaerdayi.dto.UserDto;
import com.oyaerdayi.entity.User;
import com.oyaerdayi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @PostMapping("")
    public String saveUser(@RequestBody UserDto userDto){

        try {
            User user = UserConverter.INSTANCE.convertAllUserDtoListToUserList(userDto);

            List<User> userList = userDao.findAll();

            if(userList.size()==0){

                userDao.save(user);
            }

            for (User user1 : userList) {
                if(user.getUserName().equals(user1.getUserName())){

                    return "This username already exists. Please try another username.";

                }
            }

            user = userService.save(user);
            return "User successfully saved.";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/{userName}")
    public String deleteUser(@PathVariable String userName){

        try{

            userService.deleteByUserName(userName);
            return"User has been deleted.";

        }
        catch(Exception e){
            return e.getMessage();
        }
    }

//    @PutMapping("")
//    public List<UserDto> update(@RequestBody UserDto userDto){
//
//        User user = UserConverter.INSTANCE.convertAllUserDtoListToUserList(userDto);
//
//        userService.save(user);
//
//        List<User> userList=new ArrayList<User>();
//
//        userList.add(user);
//
//        List<UserDto> userDtoList = UserConverter.INSTANCE.convertAllUserListToUserDtoList(userList);
//
//        return userDtoList;
//    }

    @GetMapping("")
    public List<UserDto> findAll(){

        List<User> userList = userService.findAll();

        List<UserDto> userDtoList = UserConverter.INSTANCE.convertAllUserListToUserDtoList(userList);

        return userDtoList;
    }
}
