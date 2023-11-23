package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Comment;

public interface CommentDao {
    public void save(Comment comment, int id);
}
