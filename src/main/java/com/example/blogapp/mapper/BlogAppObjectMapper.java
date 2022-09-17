package com.example.blogapp.mapper;

import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogdetails.Blog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogAppObjectMapper {
    ObjectMapper objectMapper;

    @Autowired
    public BlogAppObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BlogCreateDomain convertBlogCreateToBlogCreateDomain(BlogCreate blogCreate) {
        BlogCreateDomain blogCreateDomain;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogCreateDomain = objectMapper.readValue(new Gson().toJson(blogCreate), BlogCreateDomain.class);
        } catch (JsonProcessingException e) {
            throw new BlogApiException(e, e.getMessage());
        }
        return blogCreateDomain;

    }

    public Blog convertsBlogPageToBlog(BlogCreateDomain blogPage) {
        Blog blog;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blog = objectMapper.readValue(new Gson().toJson(blogPage), Blog.class);
        } catch (JsonProcessingException e) {
            throw new BlogApiException(e, e.getMessage());
        }
        return blog;
    }
}
