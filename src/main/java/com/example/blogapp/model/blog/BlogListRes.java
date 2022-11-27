package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogListRes {
    ResultStatus resultStatus;
    private List<Blog> blogList;
    private int pageNo;
    private int totalElements;
    private int totalPages;
    private boolean isLast;

}
