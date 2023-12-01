package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Comment;
import com.aditya.BlogPost.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CommentDaoImpl implements CommentDao{
    private EntityManager entityManager;
    private PostDao postDao;

    @Autowired
    public CommentDaoImpl(EntityManager entityManager, PostDao postDao) {
        this.entityManager = entityManager;
        this.postDao = postDao;
    }

    @Override
    @Transactional
    public void save(Comment comment, int id) {
        Post post = postDao.findById(String.valueOf(id));
        comment.setPost(post);

        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public void delete(String commentId) {
        Comment comment = entityManager.find(Comment.class, String.valueOf(commentId));
        entityManager.remove(comment);
    }

    @Override
    @Transactional
    public Comment findById(String commentId) {
        return entityManager.find(Comment.class, String.valueOf(commentId));
    }

    @Override
    @Transactional
    public void updateById(Comment comment){
        entityManager.merge(comment);
    }
}
