package com.example.blogapp.service;

import com.example.blogapp.config.BlogAppMessageConfig;
import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogcreate.BlogCreateResponse;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class BlogCreateService implements BlogCreatePort {

    private final BlogRepository blogRepository;
    private final BlogAppObjectMapper blogAppObjectMapper;

    private final BlogAppMessageConfig blogAppMessageConfig;

    @Autowired
    public BlogCreateService(BlogRepository blogRepository, BlogAppObjectMapper blogAppObjectMapper, BlogAppMessageConfig blogAppMessageConfig) {
        this.blogRepository = blogRepository;
        this.blogAppObjectMapper = blogAppObjectMapper;
        this.blogAppMessageConfig = blogAppMessageConfig;
    }

    @Override
    public BlogCreateResponse createBlog(BlogCreate blogCreate) {
        Blog blog;
        try {
            blog = blogAppObjectMapper.convertBlogCreateToBlogCreateDomain(blogCreate);
            Blog save = blogRepository.save(blog);
            if (save.getId() == 0) {
                throw new BlogApiException(blogAppMessageConfig.getCreationIssue());
            }

        } catch (Exception e) {
            log.info(String.format(getClass() + " " + "createBlog" + Arrays.toString(e.getStackTrace())));
            throw new BlogApiException(e.getMessage());
        }
        return makeResponse();
    }

    private BlogCreateResponse makeResponse() {
        BlogCreateResponse blogCreateResponse = new BlogCreateResponse();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatusCode("200");
        resultStatus.setStatus("Success");
        resultStatus.setMessage(blogAppMessageConfig.getSuccessMessage());

        blogCreateResponse.setResultStatus(resultStatus);
        return blogCreateResponse;
    }
}
