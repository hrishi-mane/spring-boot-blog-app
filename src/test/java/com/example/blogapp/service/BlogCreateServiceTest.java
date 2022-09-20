package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogCreateService.class})
@ExtendWith(SpringExtension.class)
class BlogCreateServiceTest {
    @MockBean
    private BlogAppObjectMapper blogAppObjectMapper;

    @Autowired
    private BlogCreateService blogCreateService;

    @MockBean
    private BlogRepository blogRepository;


    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");

        when(blogAppObjectMapper.convertBlogCreateToBlogCreateDomain(any())).thenReturn(blog);
        when(blogRepository.save(any())).thenReturn(blog);


        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        ResultStatus resultStatus = blogCreateService.createBlog(blogCreate).getResultStatus();
        assertEquals("Blog created successfully", resultStatus.getMessage());
        assertEquals("200", resultStatus.getStatusCode());
        assertEquals("Success", resultStatus.getStatus());

    }

    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlogException() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        when(blogAppObjectMapper.convertBlogCreateToBlogCreateDomain(any()))
                .thenThrow(new BlogApiException("An error occurred"));
        when(blogRepository.save(any())).thenReturn(blog);


        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));
    }

    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlogFailureOnId() {
        Blog blog = mock(Blog.class);
        when(blog.getId()).thenReturn(0);
        when(blogRepository.save(any())).thenReturn(blog);

        when(blogAppObjectMapper.convertBlogCreateToBlogCreateDomain(any())).thenReturn(blog);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));

    }
}

