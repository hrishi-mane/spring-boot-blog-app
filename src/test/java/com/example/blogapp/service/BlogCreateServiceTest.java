package com.example.blogapp.service;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class BlogCreateServiceTest {
    BlogDao blogDao;
    BlogCreateRes blogCreateRes;
    com.example.blogapp.model.blog.BlogCreate blogCreate;
    @Mock
    private BlogRepository blogRepository;
    @Mock
    private BlogObjectMapper blogObjectMapper;
    @Mock
    private BlogMessageConfig blogMessageConfig;
    private BlogCreateService blogCreateService;

    @BeforeEach
    void setUp() {
        blogCreateService = new BlogCreateService(blogRepository, blogObjectMapper, blogMessageConfig);
        blogCreate = new BlogCreate();
        blogDao = new BlogDao();
        blogCreateRes = new BlogCreateRes();
    }


    @org.junit.jupiter.api.Test
    public void testCreateBlog() {
        blogCreateRes.setId(1);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("Success");
        blogCreateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogDao(blogCreate)).thenReturn(blogDao);
        when(blogRepository.save(blogDao)).thenReturn(blogDao);
        when(blogObjectMapper.generateBlogCreateRes(blogDao)).thenReturn(blogCreateRes);

        assertEquals("Success", blogCreateService.createBlog(blogCreate).getResultStatus().getStatus());
    }


    @Test
    public void testCreateBlog2() {
        when(blogObjectMapper.generateBlogDao(blogCreate)).thenThrow(new BlogApiException("Exception occured"));
        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));
    }


    @Test
    public void testCreateBlog3() {
        when(blogObjectMapper.generateBlogDao(blogCreate)).thenReturn(blogDao);
        when(blogRepository.save(blogDao)).thenReturn(blogDao);
        when(blogObjectMapper.generateBlogCreateRes(blogDao)).thenReturn(blogCreateRes);

        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));
    }
}