package com.example.blogapp.service;

import com.example.blogapp.model.blog.blogcreate.BlogCreate;

public interface BlogCreatePort {
    BlogCreate createBlog(BlogCreate blogCreate);
}
