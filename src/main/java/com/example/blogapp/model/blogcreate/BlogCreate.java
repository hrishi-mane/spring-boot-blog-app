package com.example.blogapp.model.blogcreate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogCreate {

    @NotNull(message = "The title cannot be null")
    @NotEmpty(message = "The title cannot be empty")
    @JsonProperty("title")
    private String title;

    @NotNull(message = "The content cannot be null")
    @NotEmpty(message = "The content cannot be empty")
    @JsonProperty("content")
    private String content;


    private String publishedDate;


}
