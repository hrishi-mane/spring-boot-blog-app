package com.example.blogapp.service;

import com.example.blogapp.model.blogcreate.BlogCreate;

public interface BlogCreatePort {
    BlogCreate createBlog(BlogCreate blogCreate);
}
