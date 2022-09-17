package com.example.blogapp.service;

import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogcreate.BlogCreate;

public interface BlogCreatePort {
    ResultStatus createBlog(BlogCreate blogCreate);
}
