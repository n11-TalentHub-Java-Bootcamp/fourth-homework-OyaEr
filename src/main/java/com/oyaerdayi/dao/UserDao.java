package com.oyaerdayi.dao;

import com.oyaerdayi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
}
