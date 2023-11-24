package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.TagDao;
import com.aditya.BlogPost.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagDao tagDao;

    @Autowired
    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public void saveTag(Tag tag){

    }
}
