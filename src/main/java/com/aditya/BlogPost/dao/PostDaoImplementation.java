package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public class PostDaoImplementation implements PostDao {
    private EntityManager entityManager;

    public PostDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
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

    @Override
    @Transactional
    public void updateById(int id, String title, String content) {
        Post post = entityManager.find(Post.class, id);
        System.out.println(post);
        post.setTitle(title);
        post.setContent(content);
        post.setUpdated_at(String.valueOf(new Date()));

        entityManager.merge(post);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Post post = entityManager.find(Post.class, Integer.parseInt(id));
        entityManager.remove(post);
    }
}
