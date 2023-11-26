package com.aditya.BlogPost.dao;

import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    @Override
    public List<Post> findPostByTagIds(List<Integer> tagIds, String order) {
        Set<Post> posts= new HashSet<>();

        TypedQuery<Tag> query = entityManager.createQuery("from Tag where id in :tagIds  ", Tag.class);
        query.setParameter("tagIds", tagIds);

        List<Tag> tags = query.getResultList();
        for(Tag tag:tags){
            List<Post> postsInATag = tag.getPosts();
            for(Post post : postsInATag){
                posts.add(post);
            }
        }

        return new ArrayList<>(posts);
    }

    @Override
    public List<Post> findPostByTagIdsAndAuthors(List<Integer> tagIds, List<String> authors, String order) {
        Set<Post> posts= new HashSet<>();
        Set<String> authorsByName = new HashSet<>(authors);

        TypedQuery<Tag> query = entityManager.createQuery("from Tag where id in :tagIds  ", Tag.class);
        query.setParameter("tagIds", tagIds);

        List<Tag> tags = query.getResultList();
        for(Tag tag:tags){
            List<Post> postsInATag = tag.getPosts();
            for(Post post : postsInATag){
                if(authorsByName.contains(post.getAuthor())){
                    posts.add(post);
                }
            }
        }

        return new ArrayList<>(posts);
    }
}
