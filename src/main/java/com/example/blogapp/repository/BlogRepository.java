package com.example.blogapp.repository;

import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogCreateDomain, Integer> {

}
