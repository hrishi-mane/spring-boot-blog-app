package com.example.blogapp.controllers;

import com.example.blogapp.payload.BlogDto;
import com.example.blogapp.payload.Response;
import com.example.blogapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.blogapp.AppConstants.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogDto> saveBlog(@RequestBody BlogDto blogDto) {
        BlogDto responseBlogDto = blogService.createBlog(blogDto);
        return new ResponseEntity<>(responseBlogDto, HttpStatus.CREATED);
    }


    @GetMapping
    ResponseEntity<Response> getBlogs(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NO, required = false) int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
                                      @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        Response response = blogService.getBlogs(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("{id}")
    ResponseEntity<BlogDto> getBlogById(@PathVariable int id) {
        return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<BlogDto> updateBlog(@PathVariable int id, @RequestBody BlogDto blogDto) {
        return new ResponseEntity<>(blogService.updateBlog(id, blogDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }


}
