package com.example.blogapp.service;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogUpdate;
import com.example.blogapp.model.blog.BlogUpdateRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogUpdateService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogUpdateServiceTest {
    @MockBean
    private BlogObjectMapper blogObjectMapper;

    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private BlogUpdateService blogUpdateService;

    /**
     * Method under test: {@link BlogUpdateService#updateBlog(BlogUpdate)}
     */
    @Test
    public void testUpdateBlog() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        when(blogRepository.save(any())).thenReturn(blogDao1);
        when(blogRepository.findById(any())).thenReturn(ofResult);

        BlogDao blogDao2 = new BlogDao();
        blogDao2.setContent("Not all who wander are lost");
        blogDao2.setId(1);
        blogDao2.setPublishedDate(mock(Timestamp.class));
        blogDao2.setStatus("Status");
        blogDao2.setTitle("Dr");

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogDao(any())).thenReturn(blogDao2);
        when(blogObjectMapper.generateBlogUpdateRes(any())).thenReturn(blogUpdateRes);

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertSame(blogUpdateRes, blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).save(any());
        verify(blogRepository).findById(any());
        verify(blogObjectMapper).generateBlogDao(any());
        verify(blogObjectMapper).generateBlogUpdateRes(any());
    }

    /**
     * Method under test: {@link BlogUpdateService#updateBlog(BlogUpdate)}
     */
    @Test
    public void testUpdateBlog2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        when(blogRepository.save(any())).thenReturn(blogDao1);
        when(blogRepository.findById(any())).thenReturn(ofResult);
        when(blogObjectMapper.generateBlogDao(any())).thenThrow(new BlogApiException("An error occurred"));
        when(blogObjectMapper.generateBlogUpdateRes(any()))
                .thenThrow(new BlogApiException("An error occurred"));

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).findById(any());
        verify(blogObjectMapper).generateBlogDao(any());
    }

    /**
     * Method under test: {@link BlogUpdateService#updateBlog(BlogUpdate)}
     */
    @Test
    public void testUpdateBlog3() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(blogRepository.save(any())).thenReturn(blogDao);
        when(blogRepository.findById(any())).thenReturn(Optional.empty());

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate(mock(Timestamp.class));
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogDao(any())).thenReturn(blogDao1);
        when(blogObjectMapper.generateBlogUpdateRes(any())).thenReturn(blogUpdateRes);

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).findById(any());
    }
}

