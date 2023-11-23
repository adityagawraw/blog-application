package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;

import java.util.List;

public interface PostDao {
    public void save(Post post);
    public List<Post> findAllPosts();
    public  Post findById(String id);
    public void updateById(int id, String title, String content);
}
