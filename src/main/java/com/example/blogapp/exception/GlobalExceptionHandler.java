package com.example.blogapp.exception;

import com.example.blogapp.model.ResultStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception Handler for BlogApiException
     *
     * @param blogApiException The exception class
     * @return
     */
    @ExceptionHandler(BlogApiException.class)
    public ResultStatus handleResourceNotFoundException(BlogApiException blogApiException) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Failed");
        resultStatus.setStatusCode("ERR_404");
        resultStatus.setMessage(blogApiException.getMessage());

        return resultStatus;
    }


}
