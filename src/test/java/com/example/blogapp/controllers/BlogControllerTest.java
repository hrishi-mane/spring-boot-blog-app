package com.example.blogapp.controllers;

import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogcreate.BlogCreateResponse;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.service.BlogCreatePort;
import com.example.blogapp.service.BlogDetailsPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
    private BlogDetailsPort blogDetailsPort;

    @Autowired
    private BlogController blogController;

    @MockBean
    private BlogCreatePort blogCreatePort;

    private ResultStatus resultStatus;

    @BeforeEach
    void beforeEach() {
        resultStatus = new ResultStatus();
        resultStatus.setMessage("Not all who wander are lost");
        resultStatus.setStatus("Status");
        resultStatus.setStatusCode("Status Code");
    }

    /**
     * Method under test: {@link BlogController#createBlog(BlogCreate)}
     */
    @Test
    void testCreateBlog() throws Exception {
        BlogCreateResponse blogCreateResponse = new BlogCreateResponse();
        blogCreateResponse.setResultStatus(resultStatus);
        when(blogCreatePort.createBlog(any())).thenReturn(blogCreateResponse);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(blogCreate);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/create-blog")
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
                                        + " lost\"}}"));
    }

    /**
     * Method under test: {@link BlogController#getBlogs(int, int, String, String)}
     */
    @Test
    void testGetBlogs() throws Exception {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogResponses(new ArrayList<>());
        blogDetailsResponse.setLast(true);
        blogDetailsResponse.setPageNo(1);
        blogDetailsResponse.setResultStatus(resultStatus);
        blogDetailsResponse.setTotalElements(1);
        blogDetailsResponse.setTotalPages(1);
        when(blogDetailsPort.getBlogs(anyInt(), anyInt(), any(), any()))
                .thenReturn(blogDetailsResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-blogs");
        MockMvcBuilders.standaloneSetup(blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"blogResponses\":[],\"pageNo\":1,\"totalElements\":1,\"totalPages\":1,\"resultStatus\":{\"statusCode\":\"Status"
                                        + " Code\",\"status\":\"Status\",\"message\":\"Not all who wander are lost\"},\"last\":true}"));
    }

}

