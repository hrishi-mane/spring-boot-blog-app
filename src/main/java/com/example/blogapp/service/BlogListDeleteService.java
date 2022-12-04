package com.example.blogapp.service;

import com.example.blogapp.model.blog.BlogDeleteListRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogListDeleteService implements BlogListDelete {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogListDeleteService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDeleteListRes deleteAllBlogs() {
        blogRepository.deleteAll();
        return makeResponse();
    }

    private BlogDeleteListRes makeResponse() {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("Success");
        BlogDeleteListRes blogDeleteListRes = new BlogDeleteListRes();
        blogDeleteListRes.setResultStatus(resultStatus);
        return blogDeleteListRes;
    }
}
