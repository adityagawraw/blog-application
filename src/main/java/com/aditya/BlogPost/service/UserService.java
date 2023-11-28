package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.AuthorityDao;
import com.aditya.BlogPost.dao.UserDao;
import com.aditya.BlogPost.entity.Authority;
import com.aditya.BlogPost.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    private AuthorityDao authorityDao;

    @Autowired
    public UserService(UserDao userDao, AuthorityDao authorityDao) {
        this.userDao = userDao;
        this.authorityDao = authorityDao;
    }

    public void addUserData(String name, String email, String password, String confirmPassword) {
        User user = new User();
        user.setUsername(name);
        user.setPassword("{noop}" + password);
        user.setEmail(email);
        userDao.save(user);
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setUsername(name);
        authorityDao.save(authority);
        user.setAuthority(authority);
        userDao.save(user);
        authorityDao.save(authority);

    }
}
