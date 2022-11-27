package com.example.blogapp.repository;

import com.example.blogapp.domain.blog.BlogDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogDao, Integer> {

}
