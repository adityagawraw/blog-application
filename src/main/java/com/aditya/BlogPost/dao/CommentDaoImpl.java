package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements  CommentDao{
    private EntityManager entityManager;

    @Autowired
    public CommentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Comment comment, int id) {

    entityManager.persist(comment);
    }
}
