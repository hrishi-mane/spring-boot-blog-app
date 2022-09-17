package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogdetails.Blog;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.repository.BlogRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogDetailsService {

    private final BlogRepository blogRepository;
    private final BlogAppObjectMapper blogAppObjectMapper;

    @Autowired
    public BlogDetailsService(BlogRepository blogRepository, BlogAppObjectMapper blogAppObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogAppObjectMapper = blogAppObjectMapper;
    }


    public BlogDetailsResponse getBlogs(int pageNo, int pageSize, String sortBy, String sortDir) {
        try {
            Page<BlogCreateDomain> blogPage;
            List<Blog> blogs;
            if (pageNo == 0 || pageSize == 0 || StringUtils.isNullOrEmpty(sortBy) || StringUtils.isNullOrEmpty(sortDir)) {
                Sort sort = sortDir.equalsIgnoreCase("asc") ?
                        Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                blogPage = blogRepository.findAll(pageable);


                blogs = blogPage.getContent().stream().
                        map(blogAppObjectMapper::convertsBlogPageToBlog).toList();
            } else {
                throw new BlogApiException("PageNo, PageSize, SortBy and SortDir are compulsory");
            }

            return makeResponse(blogs, blogPage);
        } catch (Exception exp) {
            throw new BlogApiException(exp.getMessage());
        }
    }

    private BlogDetailsResponse makeResponse(List<Blog> blogs, Page<BlogCreateDomain> blogPage) {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogs(blogs);
        blogDetailsResponse.setTotalPages(blogPage.getTotalPages());
        blogDetailsResponse.setTotalElements(blogs.size());
        blogDetailsResponse.setPageNo(blogPage.getNumber());
        blogDetailsResponse.setLast(blogPage.isLast());

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatusCode("200");
        resultStatus.setStatus("Success");

        blogDetailsResponse.setResultStatus(resultStatus);

        return blogDetailsResponse;
    }
}
