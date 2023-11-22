package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.dao.PostDaoImplementation;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.PostModel;
import com.aditya.BlogPost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String getHome(Model model){
        model.addAttribute("posts", postService.getPosts());
        return  "home";
    }

@RequestMapping("/writeBlog")
public String getHomePage(Model model){
    model.addAttribute("post", new PostModel());

    return "writeBlog";
}

@RequestMapping(value = "/publish" , method = RequestMethod.POST)
public  String publishBlog(@ModelAttribute PostModel postModel, Model model){
     postService.addPost(postModel);
     System.out.println(model+ " "+ postModel);

    return "redirect:/";
}
}
