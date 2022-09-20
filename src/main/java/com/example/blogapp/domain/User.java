//package com.example.blogapp.domain;
//
//import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Entity
//@Table(name = "user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "name", length = 128)
//    private String name;
//
//    @Column(name = "password", length = 45)
//    private String password;
//
//    @Column(name = "security_question_answer", length = 45)
//    private String securityQuestionAnswer;
//
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE,
//            CascadeType.PERSIST,
//            CascadeType.REFRESH,
//            CascadeType.REMOVE})
//
//    private Set<BlogCreateDomain> blogs = new HashSet<>();
//
//
//}