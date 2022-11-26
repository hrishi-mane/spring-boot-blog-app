package com.example.blogapp.service;

import com.example.blogapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogUpdateService implements BlogUpdate {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogUpdateService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void updateBlog(int id) {

    }
}
