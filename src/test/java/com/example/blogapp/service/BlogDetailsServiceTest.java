package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BlogDetailsService.class})
@ExtendWith(SpringExtension.class)
class BlogDetailsServiceTest {
    @MockBean
    private BlogAppObjectMapper blogAppObjectMapper;

    @Autowired
    private BlogDetailsService blogDetailsService;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogDetailsService#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs() {
        when(blogRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BlogDetailsResponse actualBlogs = blogDetailsService.getBlogs(1, 3, "asc", "Sort Dir");
        assertTrue(actualBlogs.getBlogResponses().isEmpty());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(0, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        ResultStatus resultStatus = actualBlogs.getResultStatus();
        assertEquals("200", resultStatus.getStatusCode());
        assertEquals("Success", resultStatus.getStatus());
    }

    @Test
    void testGetBlogs2() {
        when(blogRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BlogDetailsResponse actualBlogs = blogDetailsService.getBlogs(1, 3, "dsc", "Sort Dir");
        assertTrue(actualBlogs.getBlogResponses().isEmpty());
        assertTrue(actualBlogs.isLast());
        assertEquals(1, actualBlogs.getTotalPages());
        assertEquals(0, actualBlogs.getTotalElements());
        assertEquals(0, actualBlogs.getPageNo());
        ResultStatus resultStatus = actualBlogs.getResultStatus();
        assertEquals("200", resultStatus.getStatusCode());
        assertEquals("Success", resultStatus.getStatus());
    }

    /**
     * Method under test: {@link BlogDetailsService#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogsException() {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("asc");
        blog.setTitle("Dr");

        ArrayList<Blog> blogList = new ArrayList<>();
        blogList.add(blog);
        PageImpl<Blog> pageImpl = new PageImpl<>(blogList);
        when(blogRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(blogAppObjectMapper.convertsBlogPageToBlog(any()))
                .thenThrow(new BlogApiException("An error occurred"));
        assertThrows(BlogApiException.class, () -> blogDetailsService.getBlogs(1, 3, "Sort By", "Sort Dir"));
    }

}

