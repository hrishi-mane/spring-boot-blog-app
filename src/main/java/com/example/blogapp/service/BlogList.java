package com.example.blogapp.service;

import com.example.blogapp.model.blog.BlogListRes;

public interface BlogList {
    BlogListRes getBlogs(int pageNo, int pageSize, String sortBy, String sortDir);
}
