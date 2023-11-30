package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.dao.TagDao;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private TagDao tagDao;
    private PostDao postDao;

    @Autowired
    public TagService(TagDao tagDao, PostDao postDao) {
        this.tagDao = tagDao;
        this.postDao = postDao;
    }

    public List<Tag> getTags(){
        return tagDao.findAll();
    }
}
