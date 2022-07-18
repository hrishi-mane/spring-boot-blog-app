package com.example.blogapp.service;

import com.example.blogapp.payload.BlogDto;
import com.example.blogapp.payload.Response;

import java.util.List;

public interface BlogService {
    BlogDto createBlog(BlogDto blogDto);

    Response getBlogs(int pageNo, int pageSize, String sortBy, String sortDir);

    BlogDto getBlogById(int id);

    BlogDto updateBlog(int id, BlogDto blogDto);

    void deleteBlog(int id);
}
