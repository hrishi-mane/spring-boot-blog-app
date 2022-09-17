package com.example.blogapp.exception;

public class BlogApiException extends RuntimeException {
    private Exception exception;
    private final String message;

    public BlogApiException(Exception exception, String message) {
        super(message);
        this.exception = exception;
        this.message = message;
    }

    public BlogApiException(String message) {
        super(message);
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
