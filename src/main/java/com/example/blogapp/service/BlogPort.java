package com.example.blogapp.service;

import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;

public interface BlogPort {
    BlogCreate createBlog(BlogCreate blogCreate);

    BlogDetailsResponse getBlogs(int pageNo, int pageSize, String sortBy, String sortDir);

    BlogCreate getBlogById(int id);

    BlogCreate updateBlog(int id, BlogCreate blogCreate);

    void deleteBlog(int id);
}
