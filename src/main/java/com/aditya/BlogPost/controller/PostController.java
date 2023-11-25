package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.CommentModel;
import com.aditya.BlogPost.model.PostFilterAndSearch;
import com.aditya.BlogPost.model.PostModel;
import com.aditya.BlogPost.service.CommentService;
import com.aditya.BlogPost.service.PostService;
import com.aditya.BlogPost.service.TagService;
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
    private CommentService commentService;
    private PostService postService;
    private PostDao postDao;
    private TagService tagService;

    @Autowired
    public PostController(CommentService commentService, PostService postService,
                          PostDao postDao, TagService tagService) {
        this.commentService = commentService;
        this.postService = postService;
        this.postDao = postDao;
    }

    @RequestMapping(value = "/")
    public String getHome(@RequestParam(value = "authorId", defaultValue = "0") List<String> authorsIds,
                          @RequestParam(value = "tagId",  defaultValue = "0") List<String> tagId,
                          @RequestParam(value = "sortField", defaultValue = "") String sortField,
                          @RequestParam(value = "order", defaultValue = "ASC") String order,
                          @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                          @RequestParam(value = "start", defaultValue = "") String start,
                          @RequestParam(value = "limit", defaultValue = "") String limit,Model model){

//        model.addAttribute("authors",);
        if(searchQuery != ""){
            model.addAttribute("posts", postService.getPostOnSearchAndFilter(searchQuery, order));
        }
        else{
            model.addAttribute("posts", postService.getPosts());
        }
        model.addAttribute("start", 12);

//        System.out.println("page number / : "+ pageNumber);
        return "home";
    }

    @RequestMapping(value = "/previousPage")
    public String getPreviousPage(){
        return "redirect:/";
    }

    @RequestMapping(value = "/nextPage")
    public String getNextPage( Model model){
        String pageNumber = (String) model.getAttribute("pageNumber");
        int nextPageNumber = 1;
        System.out.println("page: "+pageNumber+ " nextPageNumber: "+nextPageNumber);
        if(!(pageNumber == null)){
            nextPageNumber = Integer.parseInt(pageNumber) + 1;
            System.out.println("nextPageNumber from null: "+nextPageNumber);
        }

//        model.addAttribute("pageNumber", String.valueOf(nextPageNumber));
        model.addAttribute("pageNumber", "22");

        return "redirect:/";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String getPost(@RequestParam("postId") String id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comment", new CommentModel());
        model.addAttribute("comments", post.getCommentList());

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

        return "redirect:/post?postId=" + post.getId();
    }

    @RequestMapping(value = "/deletePost")
    public String deletePost(@RequestParam(value = "postId") String id) {
        postService.deletePostById(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String getHomePage(Model model) {
        model.addAttribute("post", new PostModel());

        return "writePost";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishBlog(@ModelAttribute PostModel postModel, Model model) {
        postService.addPost(postModel);
        return "redirect:/";
    }
}
