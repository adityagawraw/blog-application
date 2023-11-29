package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
