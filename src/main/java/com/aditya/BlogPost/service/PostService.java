package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.dao.PostDaoImplementation;
import com.aditya.BlogPost.dao.TagDao;
import com.aditya.BlogPost.dao.TagDaoImpl;
import com.aditya.BlogPost.entity.Post;
import com.aditya.BlogPost.entity.Tag;
import com.aditya.BlogPost.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    private PostDao postDao;
    private TagDao tagDao;


    @Autowired
    public PostService(PostDao postDao, TagDao tagDao ) {
        this.postDao = postDao;
        this.tagDao = tagDao;
    }

    public void addPost(PostModel postModel) {
        Post post = new Post();

        post.setTitle(postModel.getTitle());
        post.setContent(postModel.getBlogContent());
        if (postModel.getBlogContent().length() > 100) {
            post.setExcerpt(postModel.getBlogContent().substring(0, 100) + "...");
        } else {
            post.setExcerpt(postModel.getBlogContent() + "...");
        }
        post.setAuthor("harry");
        post.setPublishedAt(String.valueOf(new Date()));
        post.setPublished(true);
        post.setCreatedAt(String.valueOf(new Date()));
        post.setUpdatedAt(String.valueOf(new Date()));
        String[] tags = postModel.getTags().split(",");

        for (String tagStr : tags) {
            Tag tag = new Tag();
            tag.setName(tagStr);
            tag.setCreatedAt(String.valueOf(new Date()));
            tag.setUpdatedAt(String.valueOf(new Date()));
            post.addTag(tag);
        }

        postDao.save(post);
    }

    public Post updatePostById(String postId, Post post) {
        postDao.updateById(Integer.parseInt(postId), post.getTitle(), post.getContent());

        return postDao.findById(postId);
    }

    public void deletePostById(String id) {
        postDao.deleteById(id);
    }

    public List<Post> getPosts(String order) {
        return   postDao.findAllPosts(order);
    }

    public List<Post> getPostOnSearchAndFilter(List<String> authors, List<Integer> tagIds,
                                                String searchQuery, String order){
        System.out.println("aouthors: "+authors.size()+"tags : "+tagIds.size());

        if(authors.size() > 0 && tagIds.size() == 0 ){
            return postDao.findByAuthors(authors, order);
        } else if (authors.size() == 0 && tagIds.size() > 0) {
            System.out.println(tagIds);
            return  tagDao.findPostByTagId(tagIds, order);
        } else if(authors.size()>0 && tagIds.size() > 0) {
            List<Post> postsByAuthors = postDao.findByAuthors(authors, order);
            List<Post> postsByTagIds = tagDao.findPostByTagId(tagIds, order);
            Set<Post> posts = new HashSet<>();

            for(Post post: postsByAuthors){
                posts.add(post);
            }
            for(Post post: postsByTagIds){
                posts.add(post);
            }

            return  new ArrayList<>(posts);
        }
        return postDao.searchbyPostFields(searchQuery, order);
    }

    public List<String> getAuthors(){
         return postDao.findAllAuthors();
    }
}
