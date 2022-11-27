package com.example.blogapp.mapper;

import com.example.blogapp.config.BlogMessageConfig;
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

    BlogMessageConfig blogMessageConfig;

    @Autowired
    public BlogObjectMapper(ObjectMapper objectMapper, BlogMessageConfig blogMessageConfig) {
        this.objectMapper = objectMapper;
        this.blogMessageConfig = blogMessageConfig;
    }

    public BlogDao generateBlogDao(Object blogPayload) {
        BlogDao blogDao;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            /*
             * If the blogPayload is either being sent for creation or update, only then create the dao object to be
             * saved in the db.
             */
            if (blogPayload instanceof BlogCreate || blogPayload instanceof BlogUpdate){
                blogDao = objectMapper.readValue(new Gson().toJson(blogPayload), BlogDao.class);
                blogDao.setPublishedDate(String.valueOf(System.currentTimeMillis()));
                blogDao.setStatus("Under review");
            }
            else {
                log.info(String.format("getClass()%s%s%s%s", " ", "convertBlogCreateToBlogCreateDomain", " ",
                        blogMessageConfig.getIncorrectObjectTypeMessage()));
                throw new BlogApiException(blogMessageConfig.getIncorrectObjectTypeMessage());
            }

        } catch (JsonProcessingException e) {
            log.info(String.format("getClass()%s%s%s%s", " ", "convertBlogCreateToBlogCreateDomain", " ",
                    Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blogDao;

    }

    public BlogCreateRes generateBlogCreateRes(BlogDao blogDao) {
        BlogCreateRes blogCreateRes;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blogCreateRes = objectMapper.readValue(new Gson().toJson(blogDao), BlogCreateRes.class);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatus("Success");
            blogCreateRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e) {
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
            resultStatus.setMessage("Blog created successfully");
            blogDetailRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e) {
            log.error(String.format("getClass()%s%s%s%s", " ", "generateBlogDetailRes", " ", Arrays.
                    toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }

        return blogDetailRes;
    }

    public BlogUpdateRes generateBlogUpdateRes(BlogDao blogDao){
        BlogUpdateRes blogUpdateRes;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            blogUpdateRes = objectMapper.readValue(new Gson().toJson(blogDao), BlogUpdateRes.class);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatus("Success");
            resultStatus.setMessage("Blog updated successfully");
            blogUpdateRes.setResultStatus(resultStatus);
            blogUpdateRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e){
            log.error(String.format("getClass()%s%s%s%s", " ", "generateBlogUpdateRes", " ", Arrays.
                    toString(e.getStackTrace())));
            throw new BlogApiException(e, e.getMessage());
        }

        return blogUpdateRes;
    }
}
