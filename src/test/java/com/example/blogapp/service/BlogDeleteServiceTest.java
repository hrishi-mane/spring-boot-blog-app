package com.example.blogapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.BlogDeleteRes;
import com.example.blogapp.repository.BlogRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BlogDeleteService.class, BlogMessageConfig.class})
@ExtendWith(SpringExtension.class)
class BlogDeleteServiceTest {
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
    void testDeleteBlog() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        doNothing().when(blogRepository).deleteById((Integer) any());
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);
        BlogDeleteRes actualDeleteBlogResult = blogDeleteService.deleteBlog(1);
        assertEquals(1, actualDeleteBlogResult.getId());
        assertNull(actualDeleteBlogResult.getResultStatus().getStatus());
        verify(blogRepository).findById((Integer) any());
        verify(blogRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link BlogDeleteService#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog2() {
        BlogDao blogDao = new BlogDao();
        blogDao.setContent("Not all who wander are lost");
        blogDao.setId(1);
        blogDao.setPublishedDate("2020-03-01");
        blogDao.setStatus("Status");
        blogDao.setTitle("Dr");
        Optional<BlogDao> ofResult = Optional.of(blogDao);
        doThrow(new BlogApiException("An error occurred")).when(blogRepository).deleteById((Integer) any());
        when(blogRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(BlogApiException.class, () -> blogDeleteService.deleteBlog(1));
        verify(blogRepository).findById((Integer) any());
        verify(blogRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link BlogDeleteService#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog3() {
        doNothing().when(blogRepository).deleteById((Integer) any());
        when(blogRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(BlogApiException.class, () -> blogDeleteService.deleteBlog(1));
        verify(blogRepository).findById((Integer) any());
    }
}

