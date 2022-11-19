package com.example.blogapp.service;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogCreateDaoReq;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class BlogCreateService implements com.example.blogapp.service.BlogCreate {

    private final BlogRepository blogRepository;
    private final BlogObjectMapper blogObjectMapper;

    private final BlogMessageConfig blogMessageConfig;

    @Autowired
    public BlogCreateService(BlogRepository blogRepository, BlogObjectMapper blogObjectMapper,
                             BlogMessageConfig blogMessageConfig) {
        this.blogRepository = blogRepository;
        this.blogObjectMapper = blogObjectMapper;
        this.blogMessageConfig = blogMessageConfig;
    }

    @Override
    public BlogCreateRes createBlog(BlogCreate blogCreate) {
        BlogCreateDaoReq blogCreateDaoReq;
        try {
            blogCreateDaoReq = blogObjectMapper.convertBlogCreateToBlogCreateDomain(blogCreate);
            blogCreateDaoReq.setPublishedDate(String.valueOf(System.currentTimeMillis()));
            BlogCreateDaoReq save = blogRepository.save(blogCreateDaoReq);
            if (save.getId() == 0) {
                throw new BlogApiException(blogMessageConfig.getCreationIssue());
            }

        } catch (Exception e) {
            log.info(String.format("getClass()%s%s%s", " ", "createBlog", Arrays.toString(e.getStackTrace())));
            throw new BlogApiException(e.getMessage());
        }
        return makeResponse();
    }

    private BlogCreateRes makeResponse() {
        BlogCreateRes blogCreateRes = new BlogCreateRes();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("Success");
        resultStatus.setMessage(blogMessageConfig.getSuccessMessage());

        blogCreateRes.setResultStatus(resultStatus);
        return blogCreateRes;
    }
}
