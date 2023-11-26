package com.aditya.BlogPost.controller;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.CommentModel;
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
        this.tagService = tagService;
    }

    @RequestMapping(value = "/")
    public String getHome(@RequestParam(value = "author", defaultValue = "0") List<String> authors,
                          @RequestParam(value = "tagId",  defaultValue = "0") List<String> tagIds,
                          @RequestParam(value = "sortField", defaultValue = "publishedDate") String sortField,
                          @RequestParam(value = "order", defaultValue = "desc") String order,
                          @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                          @RequestParam(value = "start", defaultValue = "1") String start,
                          @RequestParam(value = "limit", defaultValue = "3") String limit,Model model){

        model.addAttribute("authors", postService.getAuthors());
        model.addAttribute("tags", tagService.getTags());
        model.addAttribute("order",order);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("start", start);
        model.addAttribute("limit", limit);

        if(authors.size()== 0 && tagIds.size() == 0 && searchQuery != ""){
            model.addAttribute("posts", postService.getPosts(order));
        }
        else{
            model.addAttribute("posts", postService.getPostOnSearchAndFilter(authors, tagIds, searchQuery, order));
        }

        model.addAttribute("start", 12);

        return "home";
    }

    @RequestMapping(value = "/previousPage")
    public String getPreviousPage(@RequestParam(value = "author", defaultValue = "0") List<String> authors,
                              @RequestParam(value = "tagId",  defaultValue = "0") List<String> tagIds,
                              @RequestParam(value = "sortField", defaultValue = "publishedDate") String sortField,
                              @RequestParam(value = "order", defaultValue = "desc") String order,
                              @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                              @RequestParam(value = "start", defaultValue = "1") String start,
                              @RequestParam(value = "limit", defaultValue = "3") String limit,Model model){
        model.addAttribute("authors",authors );
        model.addAttribute("tags", tagIds);
        model.addAttribute("order",order);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("start", start);
        model.addAttribute("limit", limit);

        return "redirect:/";
    }

    @RequestMapping(value = "/nextPage")
    public String getNextPage(@RequestParam(value = "author", defaultValue = "0") List<String> authors,
                              @RequestParam(value = "tagId",  defaultValue = "0") List<String> tagIds,
                              @RequestParam(value = "sortField", defaultValue = "publishedDate") String sortField,
                              @RequestParam(value = "order", defaultValue = "desc") String order,
                              @RequestParam(value = "searchQuery", defaultValue = "") String searchQuery,
                              @RequestParam(value = "start", defaultValue = "1") String start,
                              @RequestParam(value = "limit", defaultValue = "3") String limit,Model model){
        model.addAttribute("authors",authors );
        model.addAttribute("tags", tagIds);
        model.addAttribute("order",order);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("start", start);
        model.addAttribute("limit", limit);

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
