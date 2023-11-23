package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.PostDaoImplementation;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDaoImplementation postDao;

    public  void addPost(PostModel postModel){
        Post post = new Post();

        post.setTitle(postModel.getTitle());
        post.setContent(postModel.getBlogContent());
        if(postModel.getBlogContent().length() > 100){
            post.setExcerpt( postModel.getBlogContent().substring(0,100));
        }
        else{
            post.setExcerpt( postModel.getBlogContent());
        }
        post.setAuthor("Aditya");
        post.setPublished_at(String.valueOf(new Date()));
        post.setIs_published(true);
        post.setCreated_at(String.valueOf(new Date()));
        post.setUpdated_at(String.valueOf(new Date()));

        postDao.save(post);
    }

    public List<Post> getPosts(){
        List<Post> posts = postDao.findAllPosts();

        return  posts;
    }

    public Post updatePostById(String postId, Post post){
        postDao.updateById(Integer.parseInt(postId), post.getTitle(), post.getContent());

        return  postDao.findById(postId);
    }

    public void deletePostById(String id) {
        postDao.deleteById(id);
    }
}
