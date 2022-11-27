package com.example.blogapp.service;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Class for saving the blog to the database.
 */
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

    /**
     * The method creates and saves a blog in the database using Jpa repository.
     */
    @Override
    public BlogCreateRes createBlog(BlogCreate blogCreate) {
        BlogDao blogDao;
        BlogCreateRes blogCreateRes;
        try {
            blogDao = blogObjectMapper.generateBlogDao(blogCreate);
            blogDao.setPublishedDate(String.valueOf(System.currentTimeMillis()));
            blogCreateRes = blogObjectMapper.generateBlogCreateRes(blogRepository.save(blogDao));

            /*
             * If there was some issue creating the blog, the blog id will be null i.e. 0, so we throw an exception.
             */
            if (blogCreateRes.getId() == 0) {
                throw new BlogApiException(blogMessageConfig.getCreationIssue());
            }
        } catch (Exception e) {
            log.info(String.format("getClass()%s%s%s", " ", "createBlog", Arrays.toString(e.getStackTrace())));
            throw new BlogApiException(e.getMessage());
        }
        return blogCreateRes;
    }

}
