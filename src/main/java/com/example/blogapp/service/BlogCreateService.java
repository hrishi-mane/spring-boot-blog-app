package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
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
        BlogCreateDomain blogCreateDomain = blogAppObjectMapper.convertBlogCreateToBlogCreateDomain(blogCreate);
        BlogCreateDomain response = blogRepository.save(blogCreateDomain);
        return null;
    }
}
