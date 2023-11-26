package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public List<Post> findAllPosts(String order) {
        TypedQuery<Post> query = entityManager.createQuery("FROM Post order by id "+order, Post.class);

        return query.getResultList();
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
        post.setUpdatedAt(String.valueOf(new Date()));

        entityManager.merge(post);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Post post = entityManager.find(Post.class, Integer.parseInt(id));
        entityManager.remove(post);
    }

    @Override
    @Transactional
    public List<Post> sortByNewest() {
        TypedQuery<Post> query = entityManager.createQuery("FROM Post ORDER BY id DESC", Post.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Post> searchbyPostFields(String searchQuery, String order) {
        TypedQuery<Post> query =
                entityManager.createQuery("SELECT p FROM Post p WHERE p.author LIKE ?1" +
                        " OR p.title LIKE ?2 OR p.content LIKE ?3 ORDER BY id "+order, Post.class);
        query.setParameter(1, "%"+searchQuery+"%");
        query.setParameter(2, "%"+searchQuery+"%");
        query.setParameter(3, "%"+searchQuery+"%");

        System.out.println("order: "+order);

        return query.getResultList();
    }

    @Override
    public List<String> findAllAuthors(){
        TypedQuery<String> query=  entityManager.createQuery("select distinct author from Post", String.class);

        return query.getResultList();
    }

    @Override
    public List<Post> findByAuthors(List<String> authors, String order) {
        TypedQuery<Post> query=
                entityManager.createQuery("from Post where author in :authors order by id "+order, Post.class);
        query.setParameter("authors", authors);
        List<Post> posts = query.getResultList();
        System.out.println("authors"+authors);
        System.out.println(posts);
        return posts;
    }

    @Override
    public List<Post> findByTags(List<String> tags, String order) {
        TypedQuery<Post> query=
                entityManager.createQuery("from Post where author in :authors order by id "+order, Post.class);
        query.setParameter("authors", tags);
        List<Post> posts = query.getResultList();

        return null;
    }

    @Override
    public List<Post> findByAuthorsAndTags(List<String> authors, List<String> tags, String order) {
        return null;
    }


}
