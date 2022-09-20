package com.example.blogapp.domain.opinion;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "opinions")
public class OpinionDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "message")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;


}
