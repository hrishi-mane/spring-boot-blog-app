package com.example.blogapp.mapper;

import com.example.blogapp.domain.blog.BlogCreateDaoReq;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.Blog;
import com.example.blogapp.model.blog.BlogDetailRes;
import com.example.blogapp.model.blog.ResultStatus;
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
public class BlogObjectMapper {
    ObjectMapper objectMapper;

    @Autowired
    public BlogObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BlogCreateDaoReq convertBlogCreateToBlogCreateDomain(BlogCreate blogCreate) {
        BlogCreateDaoReq blogCreateDaoReq;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogCreateDaoReq = objectMapper.readValue(new Gson().toJson(blogCreate), BlogCreateDaoReq.class);
            blogCreateDaoReq.setPublishedDate(String.valueOf(System.currentTimeMillis()));
            blogCreateDaoReq.setStatus("Under review");
        } catch (JsonProcessingException e) {
            log.info(String.format("getClass()%s%s%s%s", " ", "convertBlogCreateToBlogCreateDomain", " ",
                    Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blogCreateDaoReq;

    }

    public Blog convertsBlogPageToBlog(BlogCreateDaoReq blogCreateDaoReq) {
        Blog blog;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blog = objectMapper.readValue(new Gson().toJson(blogCreateDaoReq), Blog.class);
        } catch (JsonProcessingException e) {
            throw new BlogApiException(e, e.getMessage());
        }
        return blog;
    }


    public BlogDetailRes generateBlogDetailRes(BlogCreateDaoReq blogCreateDaoReq) {
        BlogDetailRes blogDetailRes;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogDetailRes = objectMapper.readValue(new Gson().toJson(blogCreateDaoReq), BlogDetailRes.class);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatus("Success");
            blogDetailRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e) {
            log.info(String.format("getClass()%s%s%s%s", " ", "generateBlogDetailRes", " ", Arrays.
                    toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }

        return blogDetailRes;
    }
}
