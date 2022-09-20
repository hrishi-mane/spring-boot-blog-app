package com.example.blogapp.mapper;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogdetails.BlogResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class BlogAppObjectMapper {
    ObjectMapper objectMapper;

    @Autowired
    public BlogAppObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Blog convertBlogCreateToBlogCreateDomain(BlogCreate blogCreate) {
        Blog blog;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blog = objectMapper.readValue(new Gson().toJson(blogCreate), Blog.class);
            blog.setPublishedDate(String.valueOf(System.currentTimeMillis()));
            blog.setStatus("Under review");
        } catch (JsonProcessingException e) {
            log.info(String.format(getClass() + " " + "convertBlogCreateToBlogCreateDomain" + " " + Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blog;

    }

    public BlogResponse convertsBlogPageToBlog(Blog blog) {
        BlogResponse blogResponse;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogResponse = objectMapper.readValue(new Gson().toJson(blog), BlogResponse.class);
        } catch (JsonProcessingException e) {
            throw new BlogApiException(e, e.getMessage());
        }
        return blogResponse;
    }
}
