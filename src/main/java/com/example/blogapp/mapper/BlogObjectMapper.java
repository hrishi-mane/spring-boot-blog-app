package com.example.blogapp.mapper;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.*;
import com.example.blogapp.utils.AppConstants;
import com.example.blogapp.utils.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
            if (blogPayload instanceof BlogCreate || blogPayload instanceof BlogUpdate) {
                blogDao = objectMapper.readValue(new Gson().toJson(blogPayload), BlogDao.class);
                Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
                blogDao.setPublishedDate(timestamp);
                blogDao.setStatus("Under review");
            }
            else {
                log.info(String.format(AppConstants.CLS_MET_ERROR, " ", "convertBlogCreateToBlogCreateDomain", " ",
                        blogMessageConfig.getIncorrectObjectTypeMessage()));
                throw new BlogApiException(blogMessageConfig.getIncorrectObjectTypeMessage());
            }

        } catch (JsonProcessingException e) {
            log.info(String.format(AppConstants.CLS_MET_ERROR, " ", "convertBlogCreateToBlogCreateDomain", " ",
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
            resultStatus.setStatus(AppConstants.SUCCESS_MESSAGE);
            blogCreateRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e) {
            log.info(String.format(AppConstants.CLS_MET_ERROR, " ", "generateBlogCreateRes", " ",
                    Arrays.toString(e.getStackTrace())));

            throw new BlogApiException(e, e.getMessage());
        }
        return blogCreateRes;
    }

    public Blog generateBlogListRes(BlogDao blogDao) {
        Blog blog;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            blog = objectMapper.readValue(new Gson().toJson(blogDao), Blog.class);
            blog.setPublishedDate(DateUtil.convertToCompliantDateFormat(blogDao.getPublishedDate(),
                    "dd-MMM-yyyy hh:mm a"));
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
            blogDetailRes.setPublishedDate(DateUtil.convertToCompliantDateFormat
                    (blogDao.getPublishedDate(), "dd-MMM-yyyy hh:mm a"));
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatus(AppConstants.SUCCESS_MESSAGE);
            resultStatus.setMessage("Blog created successfully");
            blogDetailRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException | BlogApiException e) {
            log.error(String.format(AppConstants.CLS_MET_ERROR, " ", "generateBlogDetailRes", " ", Arrays.
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
            resultStatus.setStatus(AppConstants.SUCCESS_MESSAGE);
            resultStatus.setMessage("Blog updated successfully");
            blogUpdateRes.setResultStatus(resultStatus);
            blogUpdateRes.setResultStatus(resultStatus);
        } catch (JsonProcessingException e){
            log.error(String.format(AppConstants.CLS_MET_ERROR, " ", "generateBlogUpdateRes", " ", Arrays.
                    toString(e.getStackTrace())));
            throw new BlogApiException(e, e.getMessage());
        }

        return blogUpdateRes;
    }
}
