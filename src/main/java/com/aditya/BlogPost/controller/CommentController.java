package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.entity.Comment;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.CommentModel;
import com.aditya.BlogPost.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("comment") CommentModel comment){
commentService.addComment(comment);
        return "redirect:/";
    }
}
