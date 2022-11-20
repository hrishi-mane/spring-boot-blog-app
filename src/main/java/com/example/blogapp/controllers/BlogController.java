package com.example.blogapp.controllers;

import com.example.blogapp.model.blog.BlogCreate;
import com.example.blogapp.model.blog.BlogCreateRes;
import com.example.blogapp.model.blog.BlogDeleteRes;
import com.example.blogapp.model.blog.BlogListRes;
import com.example.blogapp.service.BlogDelete;
import com.example.blogapp.service.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.blogapp.utils.AppConstants.*;

@RestController
public class BlogController {
    com.example.blogapp.service.BlogCreate blogCreateService;
    BlogList blogListService;

    BlogDelete blogDeleteService;

    @Autowired
    public BlogController(com.example.blogapp.service.BlogCreate blogCreateService, BlogList blogListService,
                          BlogDelete blogDeleteService) {
        this.blogCreateService = blogCreateService;
        this.blogListService = blogListService;
        this.blogDeleteService = blogDeleteService;
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
        return blogListService.getBlogs(pageNo, pageSize, sortBy, sortDir);
    }


    @DeleteMapping(value = "/delete-blog", produces = "application/json")
    @ResponseBody
    BlogDeleteRes deleteBlog(@RequestParam(value = "blogId", required = true) int blogId) {
        return blogDeleteService.deleteBlog(blogId);
    }

}
