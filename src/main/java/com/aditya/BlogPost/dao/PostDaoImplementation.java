package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PostDaoImplementation implements PostDao {
    private EntityManager entityManager;
    private TagDao tagDao;

    @Autowired
    public PostDaoImplementation(EntityManager entityManager, TagDao tagDao) {
        this.entityManager = entityManager;
        this.tagDao = tagDao;
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
    public void updateById(int id, String title, String content, String[] tagArr) {
        Post post = entityManager.find(Post.class, id);
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(String.valueOf(new Date()));
        List<Tag> tags = post.getTags();

        for(int index = 0; index < tagArr.length;index++){
            Tag tag = new Tag();
            System.out.println(tagArr[index]);
            if(index < tags.size()){
                tag = tags.get(index);
                tag.setName(tagArr[index]);
                tag.setUpdatedAt(String.valueOf(new Date()));
                tagDao.updateTag(tag);
            }
            else{
                tag.setName(tagArr[index]);
                tag.setCreatedAt(String.valueOf(new Date()));
                tag.setUpdatedAt(String.valueOf(new Date()));
                tagDao.save(tag);
                post.addTag(tag);
            }
        }

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

        return posts;
    }
}
