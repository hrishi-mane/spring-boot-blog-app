package com.example.blogapp.payload;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<BlogDto> blogdtos;
    private int pageNo;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
}
