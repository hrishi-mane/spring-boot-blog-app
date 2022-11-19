package com.example.blogapp.model.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultStatus {
    String statusCode;
    String status;
    String message;
}
