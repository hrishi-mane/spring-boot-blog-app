package com.example.blogapp.exception.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.ResultStatus;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
     * Method under test: {@link GlobalExceptionHandler#handleBlogApiException(BlogApiException)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleBlogApiException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.blogapp.exception.handler.GlobalExceptionHandler.handleBlogApiException(GlobalExceptionHandler.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandler()).handleBlogApiException(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleMethodArgumentNotValidException() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:203)
        //       at com.example.blogapp.exception.handler.GlobalExceptionHandler.handleMethodArgumentNotValidException(GlobalExceptionHandler.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        globalExceptionHandler.handleMethodArgumentNotValidException(
                new MethodArgumentNotValidException(null, new BindException("Target", "Object Name")));
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleMethodArgumentNotValidException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:203)
        //       at com.example.blogapp.exception.handler.GlobalExceptionHandler.handleMethodArgumentNotValidException(GlobalExceptionHandler.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        MethodParameter parameter = mock(MethodParameter.class);
        globalExceptionHandler.handleMethodArgumentNotValidException(
                new MethodArgumentNotValidException(parameter, new BindException("Target", "Object Name")));
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleMethodArgumentNotValidException3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.blogapp.exception.handler.GlobalExceptionHandler.handleMethodArgumentNotValidException(GlobalExceptionHandler.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandler()).handleMethodArgumentNotValidException(null);
    }

    /**
     * Method under test: {@link GlobalExceptionHandler#handleMethodArgumentNotValidException(MethodArgumentNotValidException)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testHandleMethodArgumentNotValidException4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.blogapp.exception.handler.GlobalExceptionHandler.handleMethodArgumentNotValidException(GlobalExceptionHandler.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        (new GlobalExceptionHandler()).handleMethodArgumentNotValidException(mock(MethodArgumentNotValidException.class));
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

