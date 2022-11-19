package com.example.blogapp.exception.handler;

import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.model.blog.BlogCreateRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static BlogCreateRes makeErrorResponse(String message) {
        BlogCreateRes blogCreateRes = new BlogCreateRes();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage(message);
        resultStatus.setStatus("Failed");
        resultStatus.setStatusCode("ERR_404");

        blogCreateRes.setResultStatus(resultStatus);
        return blogCreateRes;
    }

    /**
     * Exception Handler for BlogApiException
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<BlogCreateRes> handleBlogApiException(BlogApiException exp) {
        BlogCreateRes blogCreateRes = makeErrorResponse(exp.getMessage());

        return new ResponseEntity<>(blogCreateRes, HttpStatus.OK);
    }

    /**
     * Exception Handler for data validation exception
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BlogCreateRes> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        BlogCreateRes blogCreateRes = makeErrorResponse(exp.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(blogCreateRes, HttpStatus.OK);
    }


}
