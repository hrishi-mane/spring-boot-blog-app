package com.example.blogapp.domain.blog;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data

@Entity
@Table(name = "blog")
public class BlogDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "published_date")
    private Timestamp publishedDate;

    @Column(name = "status")
    private String status;


}