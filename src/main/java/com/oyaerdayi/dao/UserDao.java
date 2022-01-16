package com.oyaerdayi.dao;

import com.oyaerdayi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    void deleteByUserName(String userName);
}
