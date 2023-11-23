package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Comment;

import java.util.List;

public interface CommentDao {
    public void save(Comment comment, int id);
    public void delete(String commentId);
}
