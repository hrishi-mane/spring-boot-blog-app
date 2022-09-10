package com.example.blogapp.model.opinion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Opinion {
    private int id;
    private String name;
    private String email;
    private String message;
}
