package com.oyaerdayi.controller;

import com.oyaerdayi.converter.UserConverter;
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

    @PostMapping("")
    public void saveUser(@RequestBody UserDto userDto){

        try {

            User user = UserConverter.INSTANCE.convertAllUserDtoListToUserList(userDto);

            user = userService.save(user);

        }
        catch(Exception e){

            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){

        try{

            userService.deleteById(id);
            return"User has been deleted.";

        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("")
    public List<UserDto> update(@RequestBody UserDto userDto){

        User user = UserConverter.INSTANCE.convertAllUserDtoListToUserList(userDto);

        userService.save(user);

        List<User> userList=new ArrayList<User>();

        userList.add(user);

        List<UserDto> userDtoList = UserConverter.INSTANCE.convertAllUserListToUserDtoList(userList);

        return userDtoList;
    }

    @GetMapping("")
    public List<UserDto> findAll(){

        List<User> userList = userService.findAll();

        List<UserDto> userDtoList = UserConverter.INSTANCE.convertAllUserListToUserDtoList(userList);

        return userDtoList;
    }
}
