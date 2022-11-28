package com.example.blogapp.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class BlogApiExceptionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogApiException#BlogApiException(Exception, String)}
     *   <li>{@link BlogApiException#getException()}
     *   <li>{@link BlogApiException#getMessage()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Exception exception = new Exception();
        BlogApiException actualBlogApiException = new BlogApiException(exception, "An error occurred");

        assertSame(exception, actualBlogApiException.getException());
        assertEquals("An error occurred", actualBlogApiException.getMessage());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BlogApiException#BlogApiException(String)}
     *   <li>{@link BlogApiException#getException()}
     *   <li>{@link BlogApiException#getMessage()}
     * </ul>
     */
    @Test
    public void testConstructor2() {
        BlogApiException actualBlogApiException = new BlogApiException("An error occurred");
        assertNull(actualBlogApiException.getException());
        assertEquals("An error occurred", actualBlogApiException.getMessage());
    }
}

