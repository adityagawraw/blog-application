package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
    private UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {
        return "authorizationError";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("confirmPassword") String confirmPassword) {
        System.out.println(name+" "+email+" "+ password);
        userService.addUserData(name, email, password, confirmPassword);
        return "redirect:/login";
    }
}
