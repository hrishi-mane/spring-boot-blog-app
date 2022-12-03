package com.example.blogapp.mapper;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogObjectMapper.class, BlogMessageConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogObjectMapperTest {
    @Autowired
    private BlogMessageConfig blogMessageConfig;

    @Autowired
    private BlogObjectMapper blogObjectMapper;

    @MockBean
    private ObjectMapper objectMapper;

    /**
     * Method under test: {@link BlogObjectMapper#generateBlogDao(Object)}
     */
    @Test
    public void testGenerateBlogDao() {
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        assertThrows(BlogApiException.class, () -> blogObjectMapper.generateBlogDao("Blog Payload"));
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
    }

    /**
     * Method under test: {@link BlogObjectMapper#generateBlogDao(Object)}
     */
    @Test
    public void testGenerateBlogDao2() throws JsonProcessingException {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(objectMapper.readValue((String) any(), (Class<BlogDao>) any())).thenReturn(blogDao);
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        BlogDao actualGenerateBlogDaoResult = blogObjectMapper.generateBlogDao(blogCreate);
        assertSame(blogDao, actualGenerateBlogDaoResult);
        assertEquals("Under review", actualGenerateBlogDaoResult.getStatus());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogDao>) any());
    }


    /**
     * Method under test: {@link BlogObjectMapper#generateBlogCreateRes(BlogDao)}
     */
    @Test
    public void testGenerateBlogCreateRes() throws JsonProcessingException {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogCreateRes blogCreateRes = new BlogCreateRes();
        blogCreateRes.setId(1);
        blogCreateRes.setResultStatus(resultStatus);
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<BlogCreateRes>) any())).thenReturn(blogCreateRes);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(timestamp);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogCreateRes actualGenerateBlogCreateResResult = blogObjectMapper.generateBlogCreateRes(blogDao);
        assertSame(blogCreateRes, actualGenerateBlogCreateResResult);
        assertEquals("Success", actualGenerateBlogCreateResResult.getResultStatus().getStatus());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogCreateRes>) any());
        verify(timestamp).getTime();
    }


    /**
     * Method under test: {@link BlogObjectMapper#generateBlogListRes(BlogDao)}
     */
    @Test
    public void testGenerateBlogListRes() throws JsonProcessingException {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<Blog>) any())).thenReturn(blog);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(timestamp);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertSame(blog, blogObjectMapper.generateBlogListRes(blogDao));
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<Blog>) any());
        verify(timestamp, atLeast(1)).getTime();
    }


    /**
     * Method under test: {@link BlogObjectMapper#generateBlogDetailRes(BlogDao)}
     */
    @Test
    public void testGenerateBlogDetailRes() throws JsonProcessingException {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDetailRes blogDetailRes = new BlogDetailRes();
        blogDetailRes.setContent("Not all who wander are lost");
        blogDetailRes.setId("42");
        blogDetailRes.setPublishedDate("2020-03-01");
        blogDetailRes.setResultStatus(resultStatus);
        blogDetailRes.setStatus("Status");
        blogDetailRes.setTitle("Dr");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<BlogDetailRes>) any())).thenReturn(blogDetailRes);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(timestamp);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogDetailRes actualGenerateBlogDetailResResult = blogObjectMapper.generateBlogDetailRes(blogDao);
        assertSame(blogDetailRes, actualGenerateBlogDetailResResult);
        ResultStatus resultStatus1 = actualGenerateBlogDetailResResult.getResultStatus();
        assertEquals("Success", resultStatus1.getStatus());
        assertEquals("Blog created successfully", resultStatus1.getMessage());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogDetailRes>) any());
        verify(timestamp, atLeast(1)).getTime();
    }

    /**
     * Method under test: {@link BlogObjectMapper#generateBlogDetailRes(BlogDao)}
     */
    @Test
    public void testGenerateBlogDetailRes2() throws JsonProcessingException {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDetailRes blogDetailRes = new BlogDetailRes();
        blogDetailRes.setContent("Not all who wander are lost");
        blogDetailRes.setId("42");
        blogDetailRes.setPublishedDate("2020-03-01");
        blogDetailRes.setResultStatus(resultStatus);
        blogDetailRes.setStatus("Status");
        blogDetailRes.setTitle("Dr");
        when(objectMapper.readValue((String) any(), (Class<Object>) any())).thenReturn("Value");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<BlogDetailRes>) any())).thenReturn(blogDetailRes);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenThrow(new BlogApiException("An error occurred"));

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(timestamp);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogObjectMapper.generateBlogDetailRes(blogDao));
    }


    /**
     * Method under test: {@link BlogObjectMapper#generateBlogUpdateRes(BlogDao)}
     */
    @Test
    public void testGenerateBlogUpdateRes() throws JsonProcessingException {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<BlogUpdateRes>) any())).thenReturn(blogUpdateRes);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(timestamp);
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogUpdateRes actualGenerateBlogUpdateResResult = blogObjectMapper.generateBlogUpdateRes(blogDao);
        assertSame(blogUpdateRes, actualGenerateBlogUpdateResResult);
        ResultStatus resultStatus1 = actualGenerateBlogUpdateResResult.getResultStatus();
        assertEquals("Blog updated successfully", resultStatus1.getMessage());
        assertEquals("Success", resultStatus1.getStatus());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogUpdateRes>) any());
        verify(timestamp).getTime();
    }


}

