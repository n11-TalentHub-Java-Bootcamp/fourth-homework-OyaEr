package com.oyaerdayi.service;

import com.oyaerdayi.dao.UserDao;
import com.oyaerdayi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;

    public User save(User user){
        return userDao.save(user);
    }

    public void deleteByUserName(String userName){
        userDao.deleteByUserName(userName);
    }

    public List<User> findAll(){

        return userDao.findAll();
    }

}