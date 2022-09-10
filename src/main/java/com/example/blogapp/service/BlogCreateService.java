package com.example.blogapp.service;

import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.blog.blogcreate.BlogCreate;
import com.example.blogapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogCreateService implements BlogCreatePort {

    private final BlogRepository blogRepository;
    private final BlogAppObjectMapper blogAppObjectMapper;

    @Autowired
    public BlogCreateService(BlogRepository blogRepository, BlogAppObjectMapper blogAppObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogAppObjectMapper = blogAppObjectMapper;
    }

    @Override
    public BlogCreate createBlog(BlogCreate blogCreate) {
        return null;
    }
}
