package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Tag;

import java.util.List;

public interface TagDao {
    public void save(Tag tag);
    public List<Tag> findAll();
}
