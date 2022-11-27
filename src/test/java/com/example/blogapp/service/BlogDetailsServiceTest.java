package com.example.blogapp.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogDetailRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {BlogDetailsService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogDetailsServiceTest {
    @Autowired
    private BlogDetailsService blogDetailsService;

    @MockBean
    private BlogObjectMapper blogObjectMapper;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogDetailsService#getBlogDetails(int)}
     */
    @Test
    public void testGetBlogDetails() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);

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
        when(blogObjectMapper.generateBlogDetailRes((BlogDao) any())).thenReturn(blogDetailRes);
        assertSame(blogDetailRes, blogDetailsService.getBlogDetails(123));
    }

    /**
     * Method under test: {@link BlogDetailsService#getBlogDetails(int)}
     */
    @Test
    public void testGetBlogDetails2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);
        when(blogObjectMapper.generateBlogDetailRes((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogDetailsService.getBlogDetails(123));
    }

    /**
     * Method under test: {@link BlogDetailsService#getBlogDetails(int)}
     */
    @Test
    public void testGetBlogDetails3() {
        when(blogRepository.findById((Integer) any())).thenReturn(Optional.empty());

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
        when(blogObjectMapper.generateBlogDetailRes((BlogDao) any())).thenReturn(blogDetailRes);
        assertThrows(BlogApiException.class, () -> blogDetailsService.getBlogDetails(123));
    }
}

