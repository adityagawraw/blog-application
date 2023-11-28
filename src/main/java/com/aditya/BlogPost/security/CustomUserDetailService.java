package com.aditya.BlogPost.security;

import com.aditya.BlogPost.dao.UserDao;
import com.aditya.BlogPost.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    private UserDao userDao;

    public CustomUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("user not found !!");
        }
        else{
            return new CustomUser(user);
        }
    }
}
