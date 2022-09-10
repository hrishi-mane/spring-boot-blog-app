package com.example.blogapp.controllers;

import com.example.blogapp.model.blog.blogcreate.BlogCreate;
import com.example.blogapp.model.blog.blogdetails.BlogDetailsResponse;
import com.example.blogapp.service.BlogPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogController.class})
@ExtendWith(SpringExtension.class)
class BlogControllerTest {
    @Autowired
    private BlogController blogController;

    @MockBean
    private BlogPort blogPort;

    /**
     * Method under test: {@link BlogController#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs() throws Exception {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogs(new ArrayList<>());
        blogDetailsResponse.setLast(true);
        blogDetailsResponse.setPageNo(1);
        blogDetailsResponse.setTotalElements(1);
        blogDetailsResponse.setTotalPages(1);
        when(blogPort.getBlogs(anyInt(), anyInt(), any(), any())).thenReturn(blogDetailsResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blogs");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"blogdtos\":[],\"pageNo\":1,\"totalElements\":1,\"totalPages\":1,\"last\":true}"));
    }

    /**
     * Method under test: {@link BlogController#getBlogById(int)}
     */
    @Test
    void testGetBlogById() throws Exception {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogs(new ArrayList<>());
        blogDetailsResponse.setLast(true);
        blogDetailsResponse.setPageNo(1);
        blogDetailsResponse.setTotalElements(1);
        blogDetailsResponse.setTotalPages(1);
        when(blogPort.getBlogs(anyInt(), anyInt(), any(), any())).thenReturn(blogDetailsResponse);
        when(blogPort.getBlogById(anyInt())).thenReturn(new BlogCreate());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blogs/{id}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"blogdtos\":[],\"pageNo\":1,\"totalElements\":1,\"totalPages\":1,\"last\":true}"));
    }

    /**
     * Method under test: {@link BlogController#updateBlog(int, BlogCreate)}
     */
    @Test
    void testUpdateBlog() {
        int id = 1;
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setId(1);
        blogCreate.setOpinions(new HashSet<>());
        blogCreate.setPublishedDate(timestamp);
        blogCreate.setStatus("Status");
        blogCreate.setTitle("Dr");
        when(blogPort.updateBlog(id, blogCreate)).thenReturn(blogCreate);
        assertEquals(blogCreate.getId(), blogController.updateBlog(id, blogCreate).getBody().getId());
    }

    /**
     * Method under test: {@link BlogController#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog() throws Exception {
        doNothing().when(blogPort).deleteBlog(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blogs/{id}", 1);
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post deleted successfully"));
    }

    /**
     * Method under test: {@link BlogController#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog2() throws Exception {
        doNothing().when(blogPort).deleteBlog(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/blogs/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post deleted successfully"));
    }

    /**
     * Method under test: {@link BlogController#saveBlog(BlogCreate)}
     */
    @Test
    void testSaveBlog() throws Exception {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogs(new ArrayList<>());
        blogDetailsResponse.setLast(true);
        blogDetailsResponse.setPageNo(1);
        blogDetailsResponse.setTotalElements(1);
        blogDetailsResponse.setTotalPages(1);
        when(blogPort.getBlogs(anyInt(), anyInt(), any(), any())).thenReturn(blogDetailsResponse);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setId(1);
        blogCreate.setOpinions(new HashSet<>());
        blogCreate.setPublishedDate(timestamp);
        blogCreate.setStatus("Status");
        blogCreate.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(blogCreate);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"blogdtos\":[],\"pageNo\":1,\"totalElements\":1,\"totalPages\":1,\"last\":true}"));
    }
}

