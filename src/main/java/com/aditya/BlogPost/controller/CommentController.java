package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.entity.Comment;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.CommentModel;
import com.aditya.BlogPost.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        return "redirect:/post?postId="+comment.getPostId();
    }
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam("commentId") String commentId, @RequestParam("postId") String postId){
        commentService.deleteComment(commentId);

        return "redirect:/post?postId="+postId;
    }

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public String getUpdateComment(@RequestParam("commentId") String commentId,
                                   @RequestParam("postId") String postId, Model model){
        model.addAttribute("comment", commentService.getCommentbyId(commentId));
        model.addAttribute("postId", postId);

        return "updateComment";
    }

    @RequestMapping(value = "/porcessUpdateComment", method = RequestMethod.POST)
    public String processUpdateComment(@RequestParam("updatedComment") String updatedComment,
                                       @RequestParam("commentId") String commentId,
                                       @RequestParam("postId") String postId,
                                       Model model){
        commentService.updateCommentById(commentId, updatedComment);

        return "redirect:/post?postId="+postId;
    }
}
