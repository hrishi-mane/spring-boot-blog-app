package com.example.blogapp.service;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.BlogDetailRes;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class BlogDetailsService implements BlogDetails {
    private BlogRepository blogRepository;
    private BlogObjectMapper blogObjectMapper;

    @Autowired
    public BlogDetailsService(BlogRepository blogRepository, BlogObjectMapper blogObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogObjectMapper = blogObjectMapper;
    }

    @Override
    public BlogDetailRes getBlogDetails(int blogId) {
        try {
            BlogDao blogDao = blogRepository.findById(blogId).orElseThrow(() ->
                    new BlogApiException("Blog does not exist"));
            return blogObjectMapper.generateBlogDetailRes(blogDao);

        } catch (Exception exp) {
            log.info(String.format("getClass()%s%s%s%s", " ", "getBlogs", " ", Arrays.toString(exp.getStackTrace())));
            throw new BlogApiException(exp.getMessage());
        }

    }

}
