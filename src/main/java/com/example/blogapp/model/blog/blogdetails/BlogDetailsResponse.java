package com.example.blogapp.model.blog.blogdetails;

import lombok.Data;

import java.util.List;

@Data
public class BlogDetailsResponse {
    private List<Blog> blogs;
    private int pageNo;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
}
