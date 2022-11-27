package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogUpdateRes {
    int id;
    ResultStatus resultStatus;

}
