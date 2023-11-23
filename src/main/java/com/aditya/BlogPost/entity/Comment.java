package com.aditya.BlogPost.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;
    @Column(name = "name")
    private  String name;
    @Column(name = "email")
    private  String email;
    @Column(name = "comment", length = 400)
    private  String comment;
    @Column(name = "created_at")
    private  String createdAt;
    @Column(name = "updated_at")
    private  String updatedAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "post_id")
    private  Post post;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment() {
    }

    public Comment(String name, String email, String comment, String created_at, String updated_at) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.createdAt = created_at;
        this.updatedAt = updated_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", created_at='" + createdAt + '\'' +
                ", updated_at='" + updatedAt + '\'' +
                ", post=" + post +
                '}';
    }
}
