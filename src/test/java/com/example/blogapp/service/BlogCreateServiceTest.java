package com.example.blogapp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BlogCreateService.class, BlogMessageConfig.class})
@ExtendWith(SpringExtension.class)
class BlogCreateServiceTest {
    @Autowired
    private BlogCreateService blogCreateService;

    @Autowired
    private BlogMessageConfig blogMessageConfig;

    @MockBean
    private BlogObjectMapper blogObjectMapper;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogCreateRes blogCreateRes = new BlogCreateRes();
        blogCreateRes.setId(1);
        blogCreateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogCreateRes((BlogDao) any())).thenReturn(blogCreateRes);
        when(blogObjectMapper.generateBlogDao((Object) any())).thenReturn(blogDao1);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        assertSame(blogCreateRes, blogCreateService.createBlog(blogCreate));
        verify(blogRepository).save((BlogDao) any());
        verify(blogObjectMapper).generateBlogDao((Object) any());
        verify(blogObjectMapper).generateBlogCreateRes((BlogDao) any());
    }

    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        when(blogObjectMapper.generateBlogCreateRes((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        when(blogObjectMapper.generateBlogDao((Object) any())).thenReturn(blogDao1);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));
        verify(blogRepository).save((BlogDao) any());
        verify(blogObjectMapper).generateBlogDao((Object) any());
        verify(blogObjectMapper).generateBlogCreateRes((BlogDao) any());
    }

    /**
     * Method under test: {@link BlogCreateService#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog3() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");
        BlogCreateRes blogCreateRes = mock(BlogCreateRes.class);
        when(blogCreateRes.getId()).thenReturn(0);
        doNothing().when(blogCreateRes).setId(anyInt());
        doNothing().when(blogCreateRes).setResultStatus((ResultStatus) any());
        blogCreateRes.setId(1);
        blogCreateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogCreateRes((BlogDao) any())).thenReturn(blogCreateRes);
        when(blogObjectMapper.generateBlogDao((Object) any())).thenReturn(blogDao1);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogCreateService.createBlog(blogCreate));
        verify(blogRepository).save((BlogDao) any());
        verify(blogObjectMapper).generateBlogDao((Object) any());
        verify(blogObjectMapper).generateBlogCreateRes((BlogDao) any());
        verify(blogCreateRes).getId();
        verify(blogCreateRes).setId(anyInt());
        verify(blogCreateRes).setResultStatus((ResultStatus) any());
    }
}

