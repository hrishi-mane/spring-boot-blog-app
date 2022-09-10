package com.example.blogapp.model.blog.blogcreate;

import com.example.blogapp.model.opinion.Opinion;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogCreate {
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("published_date")
    private Timestamp publishedDate = new Timestamp(System.currentTimeMillis());

    @JsonProperty("status")
    private String status;

    @JsonProperty("opinions")
    private Set<Opinion> opinions;

}
