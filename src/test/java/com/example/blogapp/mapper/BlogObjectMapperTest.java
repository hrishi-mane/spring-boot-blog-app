package com.example.blogapp.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.Blog;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.model.blog.BlogDetailRes;
import com.example.blogapp.model.blog.BlogUpdateRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        blogDao.setPublishedDate("2020-03-01");
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

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogCreateRes actualGenerateBlogCreateResResult = blogObjectMapper.generateBlogCreateRes(blogDao);
        assertSame(blogCreateRes, actualGenerateBlogCreateResResult);
        assertEquals("Success", actualGenerateBlogCreateResResult.getResultStatus().getStatus());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogCreateRes>) any());
    }


    /**
     * Method under test: {@link BlogObjectMapper#convertsBlogPageToBlog(BlogDao)}
     */
    @Test
    public void testConvertsBlogPageToBlog() throws JsonProcessingException {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId("42");
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<Blog>) any())).thenReturn(blog);

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        assertSame(blog, blogObjectMapper.convertsBlogPageToBlog(blogDao));
    }

    /**
     * Method under test: {@link BlogObjectMapper#convertsBlogPageToBlog(BlogDao)}
     */

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

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogDetailRes actualGenerateBlogDetailResResult = blogObjectMapper.generateBlogDetailRes(blogDao);
        assertSame(blogDetailRes, actualGenerateBlogDetailResResult);
        ResultStatus resultStatus1 = actualGenerateBlogDetailResResult.getResultStatus();
        assertEquals("Success", resultStatus1.getStatus());
        assertEquals("Blog created successfully", resultStatus1.getMessage());
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

        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        BlogUpdateRes actualGenerateBlogUpdateResResult = blogObjectMapper.generateBlogUpdateRes(blogDao);
        assertSame(blogUpdateRes, actualGenerateBlogUpdateResResult);
        ResultStatus resultStatus1 = actualGenerateBlogUpdateResResult.getResultStatus();
        assertEquals("Blog updated successfully", resultStatus1.getMessage());
        assertEquals("Success", resultStatus1.getStatus());
    }

}

