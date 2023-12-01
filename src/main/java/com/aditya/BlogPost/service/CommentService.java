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

    public void addComment(CommentModel commentModel) {
        Comment comment = new Comment();
        comment.setName(commentModel.getName());
        comment.setEmail(commentModel.getEmail());
        comment.setComment(commentModel.getComment());
        comment.setCreatedAt(String.valueOf(new Date()));
        comment.setUpdatedAt(String.valueOf(new Date()));

        commentDao.save(comment, commentModel.getPostId());
    }

    public void deleteComment(String commentId) {
        commentDao.delete(commentId);
    }

    public Comment getCommentbyId(String commentId) {
        return commentDao.findById(commentId);
    }

    public void updateCommentById(String commentId, String updatedComment) {
        Comment comment = new Comment();
        comment = commentDao.findById(commentId);
        comment.setComment(updatedComment);
        comment.setUpdatedAt(String.valueOf(new Date()));

        commentDao.updateById(comment);
    }
}
