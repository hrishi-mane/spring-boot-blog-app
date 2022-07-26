package com.example.blogapp.service;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.BlogDeleteRes;
import com.example.blogapp.repository.BlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogDeleteService.class, BlogMessageConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogDeleteServiceTest {
    @Autowired
    private BlogDeleteService blogDeleteService;

    @Autowired
    private BlogMessageConfig blogMessageConfig;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogDeleteService#deleteBlog(int)}
     */
    @Test
    public void testDeleteBlog() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        doNothing().when(blogRepository).deleteById(any());
        when(blogRepository.findById(any())).thenReturn(ofResult);
        BlogDeleteRes actualDeleteBlogResult = blogDeleteService.deleteBlog(1);
        assertEquals(1, actualDeleteBlogResult.getId());
        assertNull(actualDeleteBlogResult.getResultStatus().getStatus());
        verify(blogRepository).findById(any());
        verify(blogRepository).deleteById(any());
    }

    /**
     * Method under test: {@link BlogDeleteService#deleteBlog(int)}
     */
    @Test
    public void testDeleteBlog2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate(mock(Timestamp.class));
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        doThrow(new BlogApiException("An error occurred")).when(blogRepository).deleteById(any());
        when(blogRepository.findById(any())).thenReturn(ofResult);
        assertThrows(BlogApiException.class, () -> blogDeleteService.deleteBlog(1));
        verify(blogRepository).findById(any());
        verify(blogRepository).deleteById(any());
    }

    /**
     * Method under test: {@link BlogDeleteService#deleteBlog(int)}
     */
    @Test
    public void testDeleteBlog3() {
        doNothing().when(blogRepository).deleteById(any());
        when(blogRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(BlogApiException.class, () -> blogDeleteService.deleteBlog(1));
        verify(blogRepository).findById(any());
    }
}

