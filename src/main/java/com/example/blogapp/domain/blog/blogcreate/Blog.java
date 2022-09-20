package com.example.blogapp.domain.blog.blogcreate;

import lombok.Data;

import javax.persistence.*;


@Data

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "status")
    private String status;


}