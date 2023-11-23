package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.CommentDao;
import com.aditya.BlogPost.entity.Comment;
import com.aditya.BlogPost.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public  void addComment(CommentModel commentModel){
        Comment commentEntity = new Comment();
        commentEntity.setName(commentModel.getName());
        commentEntity.setEmail(commentModel.getEmail());
        commentEntity.setComment(commentModel.getComment());
        commentEntity.setCreated_at(String.valueOf(new Date()));
        commentEntity.setUpdated_at(String.valueOf(new Date()));

        commentDao.save(commentEntity, commentModel.getPostId());
    }
}
