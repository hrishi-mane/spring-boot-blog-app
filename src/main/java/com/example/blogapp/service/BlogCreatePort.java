package com.example.blogapp.service;

import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogcreate.BlogCreateResponse;

public interface BlogCreatePort {
    BlogCreateResponse createBlog(BlogCreate blogCreate);
}
