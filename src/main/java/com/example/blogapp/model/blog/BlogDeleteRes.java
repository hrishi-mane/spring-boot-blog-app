package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BlogDeleteRes {
    private int id;
    private ResultStatus resultStatus;
}
