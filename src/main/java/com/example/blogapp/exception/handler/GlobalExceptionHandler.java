package com.example.blogapp.exception.handler;

import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static ResultStatus makeErrorResponse(String message) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage(message);
        resultStatus.setStatus("Failed");
        resultStatus.setStatusCode("ERR_404");

        return resultStatus;
    }

    /**
     * Exception Handler for BlogApiException
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ResultStatus> handleBlogApiException(BlogApiException exp) {
        return new ResponseEntity<>(makeErrorResponse(exp.getMessage()), HttpStatus.OK);
    }

    /**
     * Exception Handler for data validation exception
     *
     * @param exp The exception class
     * @return The error response containing custom error message based on what went wrong
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultStatus> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        return new ResponseEntity<>(makeErrorResponse(Objects.requireNonNull(exp.getBindingResult().
                getFieldError()).getDefaultMessage()), HttpStatus.OK);
    }


}
