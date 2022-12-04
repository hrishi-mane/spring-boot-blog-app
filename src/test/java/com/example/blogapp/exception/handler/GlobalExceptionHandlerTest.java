package com.example.blogapp.exception.handler;

import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.ResultStatus;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {
    /**
     * Method under test: {@link GlobalExceptionHandler#handleBlogApiException(BlogApiException)}
     */
    @Test
    public void testHandleBlogApiException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<ResultStatus> actualHandleBlogApiExceptionResult = globalExceptionHandler
                .handleBlogApiException(new BlogApiException("An error occurred"));
        assertTrue(actualHandleBlogApiExceptionResult.hasBody());
        assertTrue(actualHandleBlogApiExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualHandleBlogApiExceptionResult.getStatusCode());
        ResultStatus body = actualHandleBlogApiExceptionResult.getBody();
        assertEquals("ERR_404", body.getStatusCode());
        assertEquals("An error occurred", body.getMessage());
        assertEquals("Failed", body.getStatus());
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    public void testHandleMethodArgumentNotValidException5() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getFieldError())
                .thenReturn(new FieldError("Object Name", "Field", "Default Message"));
        ResponseEntity<ResultStatus> actualHandleMethodArgumentNotValidExceptionResult = globalExceptionHandler
                .handleMethodArgumentNotValidException(new MethodArgumentNotValidException(null, beanPropertyBindingResult));
        assertTrue(actualHandleMethodArgumentNotValidExceptionResult.hasBody());
        assertTrue(actualHandleMethodArgumentNotValidExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualHandleMethodArgumentNotValidExceptionResult.getStatusCode());
        ResultStatus body = actualHandleMethodArgumentNotValidExceptionResult.getBody();
        assertEquals("ERR_404", body.getStatusCode());
        assertEquals("Default Message", body.getMessage());
        assertEquals("Failed", body.getStatus());
        verify(beanPropertyBindingResult).getFieldError();
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    public void testHandleMethodArgumentNotValidException6() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getDefaultMessage()).thenThrow(new BlogApiException("An error occurred"));
        BeanPropertyBindingResult beanPropertyBindingResult = mock(BeanPropertyBindingResult.class);
        when(beanPropertyBindingResult.getFieldError()).thenReturn(fieldError);
        assertThrows(BlogApiException.class, () -> globalExceptionHandler
                .handleMethodArgumentNotValidException(new MethodArgumentNotValidException(null, beanPropertyBindingResult)));
        verify(beanPropertyBindingResult).getFieldError();
        verify(fieldError).getDefaultMessage();
    }
}

