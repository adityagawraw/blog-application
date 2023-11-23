package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.CommentDao;
import com.aditya.BlogPost.entity.Comment;
import com.aditya.BlogPost.model.CommentModel;
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
        commentEntity.setCreatedAt(String.valueOf(new Date()));
        commentEntity.setUpdatedAt(String.valueOf(new Date()));

        commentDao.save(commentEntity, commentModel.getPostId());
    }
    public void deleteComment(String commentId){
        commentDao.delete(commentId);
    }
    public Comment getCommentbyId(String commentId){
        Comment comment = commentDao.findById(commentId);

        return comment;
    }

    public  void updateCommentById(String commentId, String updatedComment){
        commentDao.updateById(commentId, updatedComment);
    }
}
