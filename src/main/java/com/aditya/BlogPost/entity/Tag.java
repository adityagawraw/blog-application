package com.aditya.BlogPost.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;
    @Column(name = "name")
    private  String name;
    @Column(name = "created_at")
    private  String createdAt;
    @Column(name = "updated_at")
    private  String updatedAt;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
               name = "posts_tags",
               joinColumns = @JoinColumn(name = "tag_id"),
               inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addPost(Post post){
        if(posts.isEmpty()){
            posts = new ArrayList<>();
        }
        posts.add(post);
    }

    public Tag(String name, String createdAt, String updatedAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", updated_at='" + updatedAt + '\'' +
                ", posts=" + posts +
                '}';
    }
}
