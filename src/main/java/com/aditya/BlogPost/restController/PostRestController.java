package com.aditya.BlogPost.restController;

import com.aditya.BlogPost.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostRestController {
    private PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello world";
    }
}
