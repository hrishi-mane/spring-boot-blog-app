package com.example.blogapp.exception.handler;

import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogcreate.BlogCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static BlogCreateResponse makeErrorResponse(String message) {
        BlogCreateResponse blogCreateResponse = new BlogCreateResponse();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage(message);
        resultStatus.setStatus("Failed");
        resultStatus.setStatusCode("ERR_404");

        blogCreateResponse.setResultStatus(resultStatus);
        return blogCreateResponse;
    }

    /**
     * Exception Handler for BlogApiException
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<BlogCreateResponse> handleBlogApiException(BlogApiException exp) {
        BlogCreateResponse blogCreateResponse = makeErrorResponse(exp.getMessage());

        return new ResponseEntity<>(blogCreateResponse, HttpStatus.OK);
    }

    /**
     * Exception Handler for data validation exception
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BlogCreateResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        BlogCreateResponse blogCreateResponse = makeErrorResponse(exp.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(blogCreateResponse, HttpStatus.OK);
    }


}
