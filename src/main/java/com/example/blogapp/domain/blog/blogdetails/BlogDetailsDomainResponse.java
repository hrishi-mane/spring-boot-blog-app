package com.example.blogapp.domain.blog.blogdetails;

import lombok.Data;

import java.util.List;

@Data
public class BlogDetailsDomainResponse {
    private List<Blog> blogs;
    private int pageNo;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
}
