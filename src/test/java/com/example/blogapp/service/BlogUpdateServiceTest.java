package com.example.blogapp.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogUpdate;
import com.example.blogapp.model.blog.BlogUpdateRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao1);
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);

        BlogDao blogDao2 = new BlogDao();
        blogDao2.setContent("Not all who wander are lost");
        blogDao2.setId(1);
        blogDao2.setPublishedDate("2020-03-01");
        blogDao2.setStatus("Status");
        blogDao2.setTitle("Dr");

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogDao((Object) any())).thenReturn(blogDao2);
        when(blogObjectMapper.generateBlogUpdateRes((BlogDao) any())).thenReturn(blogUpdateRes);

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertSame(blogUpdateRes, blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).save((BlogDao) any());
        verify(blogRepository).findById((Integer) any());
        verify(blogObjectMapper).generateBlogDao((Object) any());
        verify(blogObjectMapper).generateBlogUpdateRes((BlogDao) any());
    }

    /**
     * Method under test: {@link BlogUpdateService#updateBlog(BlogUpdate)}
     */
    @Test
    public void testUpdateBlog2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);

        BlogDao blogDao1 = new BlogDao();
        blogDao1.setContent("Not all who wander are lost");
        blogDao1.setId(1);
        blogDao1.setPublishedDate("2020-03-01");
        blogDao1.setStatus("Status");
        blogDao1.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao1);
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);
        when(blogObjectMapper.generateBlogDao((Object) any())).thenThrow(new BlogApiException("An error occurred"));
        when(blogObjectMapper.generateBlogUpdateRes((BlogDao) any()))
                .thenThrow(new BlogApiException("An error occurred"));

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).findById((Integer) any());
        verify(blogObjectMapper).generateBlogDao((Object) any());
    }

    /**
     * Method under test: {@link BlogUpdateService#updateBlog(BlogUpdate)}
     */
    @Test
    public void testUpdateBlog3() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        when(blogRepository.save((BlogDao) any())).thenReturn(blogDao);
        when(blogRepository.findById((Integer) any())).thenReturn(Optional.empty());

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

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(blogObjectMapper.generateBlogDao((Object) any())).thenReturn(blogDao1);
        when(blogObjectMapper.generateBlogUpdateRes((BlogDao) any())).thenReturn(blogUpdateRes);

        BlogUpdate blogUpdate = new BlogUpdate();
        blogUpdate.setContent("Not all who wander are lost");
        blogUpdate.setId(1);
        blogUpdate.setTitle("Dr");
        assertThrows(BlogApiException.class, () -> blogUpdateService.updateBlog(blogUpdate));
        verify(blogRepository).findById((Integer) any());
    }
}

