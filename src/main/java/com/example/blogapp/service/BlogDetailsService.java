package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.mapper.BlogAppObjectMapper;
import com.example.blogapp.model.ResultStatus;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.model.blogdetails.BlogResponse;
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

@Slf4j
@Service
public class BlogDetailsService implements BlogDetailsPort {

    private final BlogRepository blogRepository;
    private final BlogAppObjectMapper blogAppObjectMapper;

    @Autowired
    public BlogDetailsService(BlogRepository blogRepository, BlogAppObjectMapper blogAppObjectMapper) {
        this.blogRepository = blogRepository;
        this.blogAppObjectMapper = blogAppObjectMapper;
    }

    @Override
    public BlogDetailsResponse getBlogs(int pageNo, int pageSize, String sortBy, String sortDir) {
        try {

            Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :
                    Sort.by(sortBy).descending();

            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<Blog> blogPage = blogRepository.findAll(pageable);


            List<BlogResponse> blogResponses = blogPage.getContent().stream().
                    map(blogAppObjectMapper::convertsBlogPageToBlog).toList();

            return makeResponse(blogResponses, blogPage);
        } catch (Exception exp) {
            log.info(String.format(getClass() + " " + "getBlogs" + " " + Arrays.toString(exp.getStackTrace())));
            throw new BlogApiException(exp.getMessage());
        }
    }

    private BlogDetailsResponse makeResponse(List<BlogResponse> blogResponses, Page<Blog> blogPage) {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogResponses(blogResponses);
        blogDetailsResponse.setTotalPages(blogPage.getTotalPages());
        blogDetailsResponse.setTotalElements(blogResponses.size());
        blogDetailsResponse.setPageNo(blogPage.getNumber());
        blogDetailsResponse.setLast(blogPage.isLast());

        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatusCode("200");
        resultStatus.setStatus("Success");

        blogDetailsResponse.setResultStatus(resultStatus);

        return blogDetailsResponse;
    }
}
