package com.example.blogapp.model.blogdetails;

import com.example.blogapp.model.opinion.Opinion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Blog {
    private final Timestamp publishedDate = new Timestamp(System.currentTimeMillis());
    private final String status = "Review Pending";
    private int id;
    private String title;
    private String content;
    private Set<Opinion> opinions;
}
