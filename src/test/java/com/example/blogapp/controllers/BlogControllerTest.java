package com.example.blogapp.controllers;

import com.example.blogapp.model.blog.*;
import com.example.blogapp.service.BlogUpdate;
import com.example.blogapp.service.*;
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

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogController.class})
@ExtendWith(SpringExtension.class)
class BlogControllerTest {
    @MockBean
    private BlogListDeleteService blogListDeleteService;

    @Autowired
    private BlogController blogController;

    @MockBean
    private com.example.blogapp.service.BlogCreate blogCreate;

    @MockBean
    private BlogDelete blogDelete;

    @MockBean
    private BlogDetails blogDetails;

    @MockBean
    private BlogList blogList;

    @MockBean
    private BlogUpdate blogUpdate;

    /**
     * Method under test: {@link BlogController#blogDeleteListRes()}
     */
    @Test
    void testBlogDeleteListRes() throws Exception {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);
        when(blogListDeleteService.deleteAllBlogs()).thenReturn(blogDeleteListRes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v1/delete-all-blog");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"}}"));
    }

    /**
     * Method under test: {@link BlogController#createBlog(com.example.blogapp.model.blog.BlogCreate)}
     */
    @Test
    void testCreateBlog() throws Exception {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogCreateRes blogCreateRes = new BlogCreateRes();
        blogCreateRes.setId(1);
        blogCreateRes.setResultStatus(resultStatus);
        when(blogCreate.createBlog(any())).thenReturn(blogCreateRes);

        com.example.blogapp.model.blog.BlogCreate blogCreate1 = new com.example.blogapp.model.blog.BlogCreate();
        blogCreate1.setContent("Not all who wander are lost");
        blogCreate1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(blogCreate1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/create-blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"},\"id\":1}"));
    }

    /**
     * Method under test: {@link BlogController#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs() throws Exception {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogListRes blogListRes = new BlogListRes();
        blogListRes.setBlogList(new ArrayList<>());
        blogListRes.setLast(true);
        blogListRes.setPageNo(1);
        blogListRes.setResultStatus(resultStatus);
        blogListRes.setTotalElements(1);
        blogListRes.setTotalPages(1);
        when(blogList.getBlogs(anyInt(), anyInt(), any(), any())).thenReturn(blogListRes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/get-blogs");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"},\"blogList\":[],\"pageNo\":1,\"totalElements\":1,\"totalPages\":1,\"last\":true}"));
    }

    /**
     * Method under test: {@link BlogController#deleteBlog(int)}
     */
    @Test
    void testDeleteBlog() throws Exception {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogDeleteRes blogDeleteRes = new BlogDeleteRes();
        blogDeleteRes.setId(1);
        blogDeleteRes.setResultStatus(resultStatus);
        when(blogDelete.deleteBlog(anyInt())).thenReturn(blogDeleteRes);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/delete-blog");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("blogId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"}}"));
    }

    /**
     * Method under test: {@link BlogController#getBlogDetails(int)}
     */
    @Test
    void testGetBlogDetails() throws Exception {
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
        when(blogDetails.getBlogDetails(anyInt())).thenReturn(blogDetailRes);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/get-blog-details");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"},\"id\":\"42\",\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"publishedDate\":\"2020-03-01\","
                                        + "\"status\":\"Status\"}"));
    }

    /**
     * Method under test: {@link BlogController#updateBlog(com.example.blogapp.model.blog.BlogUpdate)}
     */
    @Test
    void testUpdateBlog() throws Exception {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");

        BlogUpdateRes blogUpdateRes = new BlogUpdateRes();
        blogUpdateRes.setId(1);
        blogUpdateRes.setResultStatus(resultStatus);
        when(blogUpdate.updateBlog(any())).thenReturn(blogUpdateRes);

        com.example.blogapp.model.blog.BlogUpdate blogUpdate1 = new com.example.blogapp.model.blog.BlogUpdate();
        blogUpdate1.setContent("Not all who wander are lost");
        blogUpdate1.setId(1);
        blogUpdate1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(blogUpdate1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/update-blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"resultStatus\":{\"statusCode\":\"Status Code\",\"status\":\"Status\",\"message\":\"Not all who wander are"
                                        + " lost\"}}"));
    }
}

