package com.example.blogapp.mapper;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.*;
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

    public BlogDao generateBlogDao(BlogCreate blogCreate) {
        BlogDao blogDao;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogDao = objectMapper.readValue(new Gson().toJson(blogCreate), BlogDao.class);
            blogDao.setPublishedDate(String.valueOf(System.currentTimeMillis()));
            blogDao.setStatus("Under review");
        } catch (JsonProcessingException e) {
            log.info(String.format("getClass()%s%s%s%s", " ", "convertBlogCreateToBlogCreateDomain", " ",
                    Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blogDao;

    }

    public BlogCreateRes generateBlogCreateRes(BlogDao blogDao){
        BlogCreateRes blogCreateRes;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            blogCreateRes = objectMapper.readValue(new Gson().toJson(blogDao), BlogCreateRes.class);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatus("Success");
            blogCreateRes.setResultStatus(resultStatus);
        } catch(JsonProcessingException e){
            log.info(String.format("getClass()%s%s%s%s", " ", "generateBlogCreateRes", " ",
                    Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blogCreateRes;
    }

    public Blog convertsBlogPageToBlog(BlogDao blogDao) {
        Blog blog;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blog = objectMapper.readValue(new Gson().toJson(blogDao), Blog.class);
        } catch (JsonProcessingException e) {
            throw new BlogApiException(e, e.getMessage());
        }
        return blog;
    }


    public BlogDetailRes generateBlogDetailRes(BlogDao blogDao) {
        BlogDetailRes blogDetailRes;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogDetailRes = objectMapper.readValue(new Gson().toJson(blogDao), BlogDetailRes.class);
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
