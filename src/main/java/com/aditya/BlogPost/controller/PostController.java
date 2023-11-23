package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.CommentModel;
import com.aditya.BlogPost.model.PostModel;
import com.aditya.BlogPost.service.PostService;
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

        return "home";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String getPost(@RequestParam("postId") String id, Model model) {
        model.addAttribute("post", postDao.findById(id));
        model.addAttribute("comment", new CommentModel());

        return "post";
    }

    @RequestMapping(value = "/editPost")
    public String editPostPage(@RequestParam(value = "postId") String id, Model model) {
        model.addAttribute("post", postDao.findById(id));

        return "editPost";
    }

    @RequestMapping(value = "/updatePost")
    public String processUpdate(@ModelAttribute("post") Post post, Model model) {
        postService.updatePostById(String.valueOf(post.getId()), post);

        return "redirect:/post?postId="+post.getId();
    }

    @RequestMapping(value = "/deletePost")
    public String deletePost(@RequestParam(value = "postId") String id) {
        postService.deletePostById(id);

        return "redirect:/";
    }

    @RequestMapping("/newpost")
    public String getHomePage(Model model) {
        model.addAttribute("post", new PostModel());

        return "writePost";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishBlog(@ModelAttribute PostModel postModel, Model model) {
        postService.addPost(postModel);
//        System.out.println(model + " " + postModel);

        return "redirect:/";
    }
}
