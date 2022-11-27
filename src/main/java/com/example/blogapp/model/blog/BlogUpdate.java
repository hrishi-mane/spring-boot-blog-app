package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogUpdate {
    @NotNull(message = "The id cannot be empty/null")
    @NotEmpty(message = "The id cannot be empty/null")
    @JsonProperty("id")
    private int id;

    @NotNull(message = "The title cannot be empty/null")
    @NotEmpty(message = "The title cannot be empty/null")
    @JsonProperty("title")
    private String title;

    @NotNull(message = "The content cannot be empty/null")
    @NotEmpty(message = "The content cannot be empty/null")
    @JsonProperty("content")
    private String content;
}
