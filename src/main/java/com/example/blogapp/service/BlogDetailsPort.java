package com.example.blogapp.service;

import com.example.blogapp.model.blogdetails.BlogDetailsResponse;

public interface BlogDetailsPort {
    BlogDetailsResponse getBlogs(int pageNo, int pageSize, String sortBy, String sortDir);
}
