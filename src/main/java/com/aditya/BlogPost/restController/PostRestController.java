package com.aditya.BlogPost.restController;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;
import com.aditya.BlogPost.model.PostModel;
import com.aditya.BlogPost.service.PostService;
import com.aditya.BlogPost.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostRestController {
    private PostService postService;
    private PostDao postDao;
    private TagService tagService;

    @Autowired
    public PostRestController(PostService postService, PostDao postDao, TagService tagService) {
        this.postService = postService;
        this.postDao = postDao;
        this.tagService = tagService;
    }

    @GetMapping("/allPosts/{order}")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable String order){
        List<Post> posts = new ArrayList<>();
        posts = postDao.findAllPosts(order);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/postById/{id}")
    public Post getPostById(@PathVariable String id){
        return postService.getPostById(id);
    }
    @GetMapping("/authors")
    public List<String> getAuthors(){
        return postService.getAuthors();
    }
    @GetMapping("/tags")
    public List<Tag> getTags(){
        return tagService.getTags();
    }

    @GetMapping("deletePost/{id}")
    public String deletePostById(@PathVariable String id){
        postService.deletePostById(id);

        return "Post deleted Successfully!!";
    }

    @PostMapping("/publish")
    public String publishPost(@RequestBody PostModel postModel){
        postService.addPost(postModel, "Aditya");

        return  "Post created successfully!!";
    }

}
