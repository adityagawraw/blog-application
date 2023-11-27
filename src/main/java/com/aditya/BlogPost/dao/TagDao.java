package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;

import java.util.List;

public interface TagDao {
    public void save(Tag tag);
    public List<Tag> findAll();
    public Tag findTagFields(String field, String value);
    public List<Post> findPostByTagIds(List<Integer> tagIds, String order);
    public List<Post> findPostByTagIdsAndAuthors(List<Integer> tagIds, List<String> authors, String order);

}
