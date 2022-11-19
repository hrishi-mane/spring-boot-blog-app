package com.example.blogapp.controllers;

import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.model.blog.BlogListRes;
import com.example.blogapp.service.BlogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.blogapp.utils.AppConstants.*;

@RestController
public class BlogController {
    com.example.blogapp.service.BlogCreate blogCreateService;
    BlogDetails blogDetailsService;

    @Autowired
    public BlogController(com.example.blogapp.service.BlogCreate blogCreateService, BlogDetails blogDetailsService) {
        this.blogCreateService = blogCreateService;
        this.blogDetailsService = blogDetailsService;
    }

    @PostMapping(value = "/create-blog", produces = "application/json")

    public BlogCreateRes createBlog(@Valid @RequestBody BlogCreate blogCreate) {
        return blogCreateService.createBlog(blogCreate);
    }


    @GetMapping(value = "/get-blogs", produces = "application/json")
    @ResponseBody
    BlogListRes getBlogs(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NO, required = false) int pageNo,
                         @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE,
                                                    required = false) int pageSize,
                         @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY,
                                                    required = false) String sortBy,
                         @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION,
                                                    required = false) String sortDir) {
        return blogDetailsService.getBlogs(pageNo, pageSize, sortBy, sortDir);
    }

}
