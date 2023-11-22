package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImplementation implements  PostDao{
    private EntityManager entityManager;
    public PostDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
        System.out.println("postdao created");
    }

    @Override
    @Transactional
    public void save(Post post) {
    entityManager.persist(post);
    }

    @Override
    @Transactional
    public List<Post> findAllPosts() {
        TypedQuery<Post> query = entityManager.createQuery("FROM Post", Post.class);
        List<Post> posts = query.getResultList();
        return posts;
    }

    @Override
    @Transactional
    public Post findById(String id) {
        return entityManager.find(Post.class, Integer.parseInt(id));
    }
}
