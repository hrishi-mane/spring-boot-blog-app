package com.example.blogapp.controllers;

import com.example.blogapp.model.blog.*;
import com.example.blogapp.service.BlogDelete;
import com.example.blogapp.service.BlogDetails;
import com.example.blogapp.service.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.example.blogapp.utils.AppConstants.*;

@RestController
public class BlogController {
    public static final String EMPTY_ID_MESSAGE = "The id cannot be null/empty";
    com.example.blogapp.service.BlogCreate blogCreateService;
    BlogList blogListService;

    BlogDelete blogDeleteService;

    BlogDetails blogDetailsService;

    @Autowired
    public BlogController(com.example.blogapp.service.BlogCreate blogCreateService, BlogList blogListService,
                          BlogDelete blogDeleteService, BlogDetails blogDetailsService) {
        this.blogCreateService = blogCreateService;
        this.blogListService = blogListService;
        this.blogDeleteService = blogDeleteService;
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
        return blogListService.getBlogs(pageNo, pageSize, sortBy, sortDir);
    }


    @DeleteMapping(value = "/delete-blog", produces = "application/json")
    @ResponseBody
    BlogDeleteRes deleteBlog(@RequestParam(value = "blogId", required = true) int blogId) {
        return blogDeleteService.deleteBlog(blogId);
    }

    @GetMapping(value = "/get-blog-details", produces = "application/json")
    @ResponseBody
    BlogDetailRes getBlogDetails(@NotEmpty(message = EMPTY_ID_MESSAGE) @NotNull(message = EMPTY_ID_MESSAGE)
                                 @RequestParam int id){
        return blogDetailsService.getBlogDetails(id);
    }

    @GetMapping(value = "/update-blog", produces = "application/json")
    void updateBlog(@RequestParam int id){

    }

}
