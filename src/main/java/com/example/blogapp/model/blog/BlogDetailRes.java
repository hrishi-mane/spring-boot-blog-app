package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BlogDetailRes {
    private ResultStatus resultStatus;
    private String id;
    private String title;
    private String content;
    private String publishedDate;
    private String status;
}
