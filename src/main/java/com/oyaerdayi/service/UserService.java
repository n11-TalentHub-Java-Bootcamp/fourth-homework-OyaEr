package com.oyaerdayi.service;

import com.oyaerdayi.dao.UserDao;
import com.oyaerdayi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User save(User user){
        return userDao.save(user);
    }

    public void deleteById(Long id){
        userDao.deleteById(id);
    }

    public List<User> findAll(){

        return userDao.findAll();
    }

}
