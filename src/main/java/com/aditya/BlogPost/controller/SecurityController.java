package com.aditya.BlogPost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @PostMapping("/login")
    public String getLogin(){
        return  "login";
    }
    @PostMapping("/register")
    public String getRegister(){
        return  "register";
    }
    @PostMapping("/processAuthentication")
    public String processAuthentication(){
        return  "register";
    }
}
