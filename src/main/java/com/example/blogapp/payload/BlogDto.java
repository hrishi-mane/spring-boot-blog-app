package com.example.blogapp.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDto {
    private int id;
    private String title;
    private String content;
    private Timestamp publishedDate = new Timestamp(System.currentTimeMillis());
    private String status = "Review Pending";
    private Set<OpinionDto> opinions;

}
