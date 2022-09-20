package com.example.blogapp.mapper;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogdetails.BlogResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BlogAppObjectMapper.class})
@ExtendWith(SpringExtension.class)
class BlogAppObjectMapperTest {
    @Autowired
    private BlogAppObjectMapper blogAppObjectMapper;

    @MockBean
    private ObjectMapper objectMapper;

    /**
     * Method under test: {@link BlogAppObjectMapper#convertBlogCreateToBlogCreateDomain(BlogCreate)}
     */
    @Test
    void testConvertBlogCreateToBlogCreateDomain() throws JsonProcessingException {
        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<Blog>) any())).thenReturn(blog);

        BlogCreate blogCreate = new BlogCreate();
        blogCreate.setContent("Not all who wander are lost");
        blogCreate.setTitle("Dr");
        Blog actualConvertBlogCreateToBlogCreateDomainResult = blogAppObjectMapper
                .convertBlogCreateToBlogCreateDomain(blogCreate);
        assertSame(blog, actualConvertBlogCreateToBlogCreateDomainResult);
        assertEquals("Under review", actualConvertBlogCreateToBlogCreateDomainResult.getStatus());
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<Blog>) any());
    }


    /**
     * Method under test: {@link BlogAppObjectMapper#convertsBlogPageToBlog(Blog)}
     */
    @Test
    void testConvertsBlogPageToBlog() throws JsonProcessingException {
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setContent("Not all who wander are lost");
        blogResponse.setPublishedDate("2020-03-01");
        blogResponse.setStatus("Status");
        blogResponse.setTitle("Dr");
        when(objectMapper.configure((DeserializationFeature) any(), anyBoolean())).thenReturn(objectMapper);
        when(objectMapper.readValue((String) any(), (Class<BlogResponse>) any())).thenReturn(blogResponse);

        Blog blog = new Blog();
        blog.setContent("Not all who wander are lost");
        blog.setId(1);
        blog.setPublishedDate("2020-03-01");
        blog.setStatus("Status");
        blog.setTitle("Dr");
        assertSame(blogResponse, blogAppObjectMapper.convertsBlogPageToBlog(blog));
        verify(objectMapper).configure((DeserializationFeature) any(), anyBoolean());
        verify(objectMapper).readValue((String) any(), (Class<BlogResponse>) any());
    }

}

