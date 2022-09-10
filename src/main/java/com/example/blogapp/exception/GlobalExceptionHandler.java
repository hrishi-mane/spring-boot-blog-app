package com.example.blogapp.exception;

import com.example.blogapp.domain.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Handler for ResourceNotFound
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFound resourceNotFound,
                                                                       WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(new Date(), resourceNotFound.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    //Handler for BlogApiException
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(BlogApiException blogApiException,
                                                                       WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(new Date(), blogApiException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    //Generic handler for any other type of exception.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(Exception exception,
                                                                       WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
