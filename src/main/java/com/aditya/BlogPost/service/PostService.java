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
    public PostService(PostDao postDao, TagDao tagDao) {
        this.postDao = postDao;
        this.tagDao = tagDao;
    }

    public void addPost(PostModel postModel, String username) {
        Post post = new Post();

        post.setTitle(postModel.getTitle());
        post.setContent(postModel.getBlogContent());

        if (postModel.getBlogContent().length() > 100) {
            post.setExcerpt(postModel.getBlogContent().substring(0, 100) + "...");
        } else {
            post.setExcerpt(postModel.getBlogContent() + "...");
        }
        post.setAuthor(username);
        post.setPublishedAt(String.valueOf(new Date()));
        post.setPublished(true);
        post.setCreatedAt(String.valueOf(new Date()));
        post.setUpdatedAt(String.valueOf(new Date()));
        String[] tags = postModel.getTags().split(",");
        for (String tagStr : tags) {
            Tag tag = new Tag();

            if (tagDao.findTagByField("name", tagStr) == null) {
                tag.setName(tagStr);
                tag.setCreatedAt(String.valueOf(new Date()));
                tag.setUpdatedAt(String.valueOf(new Date()));
            } else {
                tag = tagDao.findTagByField("name", tagStr);
            }

            post.addTag(tag);
        }

        postDao.save(post);
    }

    public Post updatePostById(String postId, String[] tagArr, Post post) {
        postDao.updateById(Integer.parseInt(postId), post.getTitle(), post.getContent(), tagArr);

        return postDao.findById(postId);
    }

    public void deletePostById(String id) {
        postDao.deleteById(id);
    }

    public List<Post> getPosts(String order, Integer start, Integer limit) {
        return getPaginatedPosts(postDao.findAllPosts(order), start, limit);
    }

    public List<Post> getPostOnSearchAndFilter(List<String> authors, List<Integer> tagIds,
                                               String searchQuery, String order, Integer start, Integer limit) {
        List<Post> posts = new ArrayList<>();

        if (!searchQuery.isEmpty()) {
            posts = postDao.searchbyPostFields(searchQuery, order);
            if(!authors.isEmpty() || !tagIds.isEmpty()){
                Set<String> postIds = new HashSet<>();

                for(Post post:posts){
                    postIds.add(String.valueOf(post.getId()));
                }

                if(tagIds.isEmpty()){
                   posts = postDao.findByAuthors(authors, order);
                } else if (authors.isEmpty()) {
                    posts = tagDao.findPostByTagIds(tagIds, order);
                }
                else {
                    posts = tagDao.findPostByTagIdsAndAuthors(tagIds, authors, order);
                }

                List<Post> filteredPosts = new ArrayList<>();

                for(Post post : posts){
                    if(postIds.contains(post.getId())){
                        filteredPosts.add(post);
                    }
                }

                return filteredPosts;
            }
            else {
                return  posts;
            }
        } else {
            if (!authors.isEmpty() && tagIds.isEmpty()) {
                posts = postDao.findByAuthors(authors, order);
            } else if (authors.isEmpty() && !tagIds.isEmpty()) {
                posts = tagDao.findPostByTagIds(tagIds, order);
            } else if (!authors.isEmpty() && !tagIds.isEmpty()) {
                posts = tagDao.findPostByTagIdsAndAuthors(tagIds, authors, order);
            }
        }

        return getPaginatedPosts(posts, start, limit);
    }

    public List<Post> getPaginatedPosts(List<Post> posts, Integer start, Integer limit) {
        int lastIndex = 1;

        if (start >= posts.size()) {
            start = posts.size();
            lastIndex = posts.size();
        } else if (start + limit - 1 >= posts.size()) {
            lastIndex = posts.size();
        } else {
            lastIndex = start + limit;
        }

        if (posts.isEmpty()) {
            return null;
        }

        return posts.subList(start - 1, lastIndex);
    }

    public List<String> getAuthors() {
        return postDao.findAllAuthors();
    }

    public Post getPostById(String id) {
        return postDao.findById(id);
    }
}

