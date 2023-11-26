package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    private EntityManager entityManager;

    @Autowired
    public TagDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Tag tag) {
    entityManager.persist(tag);
    }

    @Override
    public List<Tag> findAll() {
        TypedQuery<Tag> query = entityManager.createQuery("from Tag", Tag.class);

        return query.getResultList();
    }
}
