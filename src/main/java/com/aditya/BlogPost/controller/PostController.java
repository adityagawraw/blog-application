package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.dao.PostDaoImplementation;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.PostModel;
import com.aditya.BlogPost.service.PostService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostDao postDao;

    @RequestMapping("/")
    public String getHome(Model model) {
        model.addAttribute("posts", postService.getPosts());
//        System.out.println(postService.getPosts());

        return "home";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String getPost(@RequestParam("postId") String id, Model model) {
        System.out.println("post id: "+id);
        model.addAttribute("post", postDao.findById(id));


        return "post";
    }
    @RequestMapping("/newpost")
    public String getHomePage(Model model) {
        model.addAttribute("post", new PostModel());

        return "writePost";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishBlog(@ModelAttribute PostModel postModel, Model model) {
        postService.addPost(postModel);
        System.out.println(model + " " + postModel);

        return "redirect:/";
    }
}
