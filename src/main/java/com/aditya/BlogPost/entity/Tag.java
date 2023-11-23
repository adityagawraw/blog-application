package com.aditya.BlogPost.entity;

import jakarta.persistence.*;

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
    private  String created_at;
    @Column(name = "updated_at")
    private  String updated_at;


}
