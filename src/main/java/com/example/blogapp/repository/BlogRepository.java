package com.example.blogapp.repository;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
