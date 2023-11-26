package com.aditya.BlogPost.service;

import com.aditya.BlogPost.dao.PostDao;
import com.aditya.BlogPost.dao.TagDao;
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

    public List<Post> getPosts(String order, Integer start, Integer limit) {
        return getPaginatedPosts(postDao.findAllPosts(order), start, limit);
    }

    public List<Post> getPostOnSearchAndFilter(List<String> authors, List<Integer> tagIds,
                                                String searchQuery, String order, Integer start, Integer limit){
        if(!authors.isEmpty() && tagIds.isEmpty()){
            return getPaginatedPosts(postDao.findByAuthors(authors, order), start, limit);
        } else if (authors.isEmpty() && !tagIds.isEmpty()) {
            System.out.println(tagIds);
            return  tagDao.findPostByTagIds(tagIds, order);
        } else if(!authors.isEmpty() && !tagIds.isEmpty()) {

            return  tagDao.findPostByTagIdsAndAuthors(tagIds, authors, order);
        }
        return postDao.searchbyPostFields(searchQuery, order);
    }
    public List<Post> getPaginatedPosts(List<Post> posts, Integer start, Integer limit){
        int lastIndex = start +limit;
        if(start >= posts.size()){
            start = posts.size() - limit -1;
            System.out.println("start: "+start+" last: "+lastIndex);
        }
        else if(start+limit-1 >= posts.size()){
            lastIndex = posts.size()-1;
             System.out.println("start: "+start+" last: "+lastIndex);
        }
         else{
             lastIndex = start +limit;
             System.out.println("start: "+start+" last: "+lastIndex+" post size: "+ posts.size());
         }
        List<Post> paginatedPosts = posts.subList(start-1, lastIndex);
        return  paginatedPosts;
    }
    public List<String> getAuthors(){
         return postDao.findAllAuthors();
    }
}
