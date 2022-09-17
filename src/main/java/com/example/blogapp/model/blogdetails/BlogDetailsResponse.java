package com.example.blogapp.model.blogdetails;

import com.example.blogapp.model.ResultStatus;
import lombok.Data;

import java.util.List;

@Data
public class BlogDetailsResponse {
    private List<Blog> blogs;
    private int pageNo;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
    ResultStatus resultStatus;
}
