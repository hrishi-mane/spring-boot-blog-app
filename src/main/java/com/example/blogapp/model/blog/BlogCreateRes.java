package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogCreateRes {
    @JsonProperty("id")
    int id;
    private ResultStatus resultStatus;
}
