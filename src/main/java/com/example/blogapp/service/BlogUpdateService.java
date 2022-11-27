package com.example.blogapp.service;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogUpdateRes;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service class that will update the blog.
 */
@Service
@Slf4j
public class BlogUpdateService implements BlogUpdate {
    private final BlogRepository blogRepository;
    private final BlogObjectMapper blogObjectMapper;
    @Autowired
    public BlogUpdateService(BlogRepository blogRepository, BlogObjectMapper blogObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogObjectMapper = blogObjectMapper;
    }

    /**
     * The method checks if the blog to be updated exists in the database and then performs the update.
     * @param blogUpdate The updated payload of a blog.
     * @return Returns the BlogUpdateRes object containing the resultStatus and blog id of the updated blog
     */
    @Override
    public BlogUpdateRes updateBlog(com.example.blogapp.model.blog.BlogUpdate blogUpdate) {
        BlogUpdateRes blogUpdateRes;
        BlogDao blogDao;
        try {
            /*
             * If blog to be updated does not exist we throw an exception.
             */
            if (blogRepository.findById(blogUpdate.getId()).isPresent()) {
                blogDao = blogObjectMapper.generateBlogDao(blogUpdate);
                blogUpdateRes = blogObjectMapper.generateBlogUpdateRes(blogRepository.save(blogDao));
            } else {
                throw new BlogApiException("Blog to be updated does not exists!");
            }
        } catch (BlogApiException e) {
            log.info(String.format("getClass()%s%s%s", " ", "updateBlog", Arrays.toString(e.getStackTrace())));
            throw new BlogApiException(e.getMessage());
        }

        return blogUpdateRes;
    }
}
