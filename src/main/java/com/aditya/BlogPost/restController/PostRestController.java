package com.aditya.BlogPost.restController;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {
    private PostService postService;
    private PostDao postDao;

    @Autowired
    public PostRestController(PostService postService, PostDao postDao) {
        this.postService = postService;
        this.postDao = postDao;
    }

    @GetMapping("/hello")
    public List<Post> getAllPosts(){
        List<Post> posts = new ArrayList<>();
        posts = postDao.findAllPosts("ASC");
        System.out.println(posts);
        return posts;
    }
}
