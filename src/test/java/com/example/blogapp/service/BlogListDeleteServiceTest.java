package com.example.blogapp.service;

import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {BlogListDeleteService.class})
@ExtendWith(SpringExtension.class)
class BlogListDeleteServiceTest {
    @Autowired
    private BlogListDeleteService blogListDeleteService;

    @MockBean
    private BlogRepository blogRepository;

    /**
     * Method under test: {@link BlogListDeleteService#deleteAllBlogs()}
     */
    @Test
    void testDeleteAllBlogs() {
        doNothing().when(blogRepository).deleteAll();
        assertEquals("Success", blogListDeleteService.deleteAllBlogs().getResultStatus().getStatus());
        verify(blogRepository).deleteAll();
    }
}

