package com.aditya.BlogPost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String getLogin(){
        return  "login";
    }
    @GetMapping("/register")
    public String getRegister(){
        return  "register";
    }
//    @PostMapping("/processAuthentication")
//    public String processAuthentication(){
//        return  "register";
//    }
@GetMapping("/access-denied")
public String getAccessDeniedPage(){
    return  "authorizationError";
}

}
