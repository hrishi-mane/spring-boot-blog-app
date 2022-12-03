package com.example.blogapp.service;

import com.example.blogapp.domain.blog.BlogDao;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogObjectMapper;
import com.example.blogapp.model.blog.Blog;
import com.example.blogapp.model.blog.BlogListRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogListService implements BlogList {

    private final BlogRepository blogRepository;
    private final BlogObjectMapper blogObjectMapper;

    @Autowired
    public BlogListService(BlogRepository blogRepository, BlogObjectMapper blogObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogObjectMapper = blogObjectMapper;
    }

    @Override
    public BlogListRes getBlogs(int pageNo, int pageSize, String sortBy, String sortDir) {
        try {

            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :
                    Sort.by(sortBy).descending();

            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<BlogDao> blogPage = blogRepository.findAll(pageable);


            List<Blog> blogRespons = blogPage.getContent().stream().
                    map(blogObjectMapper::generateBlogListRes).collect(Collectors.toList());

            return makeResponse(blogRespons, blogPage);
        } catch (Exception exp) {
            log.info(String.format("getClass()%s%s%s%s", " ", "getBlogs"," ", Arrays.toString(exp.getStackTrace())));
            throw new BlogApiException(exp.getMessage());
        }
    }

    private BlogListRes makeResponse(List<Blog> blogRespons, Page<BlogDao> blogPage) {
        BlogListRes blogListRes = new BlogListRes();

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus("Success");

        blogListRes.setResultStatus(resultStatus);
        blogListRes.setBlogList(blogRespons);
        blogListRes.setTotalPages(blogPage.getTotalPages());
        blogListRes.setTotalElements(blogRespons.size());
        blogListRes.setPageNo(blogPage.getNumber());
        blogListRes.setLast(blogPage.isLast());



        return blogListRes;
    }
}
