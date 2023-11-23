package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentDaoImpl implements  CommentDao{
    private EntityManager entityManager;

    @Autowired
    public CommentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Comment comment) {
    entityManager.persist(comment);
    }
}
