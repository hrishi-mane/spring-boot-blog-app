package com.example.blogapp.controllers;

import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogcreate.BlogCreateResponse;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.service.BlogCreatePort;
import com.example.blogapp.service.BlogDetailsPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.blogapp.utils.AppConstants.*;

@RestController
public class BlogController {
    BlogCreatePort blogCreatePort;
    BlogDetailsPort blogDetailsPort;
    //update
    @Autowired
    public BlogController(BlogCreatePort blogCreatePort, BlogDetailsPort blogDetailsPort) {
        this.blogCreatePort = blogCreatePort;
        this.blogDetailsPort = blogDetailsPort;
    }

    @PostMapping("/create-blog")
    public ResponseEntity<BlogCreateResponse> createBlog(@Valid @RequestBody BlogCreate blogCreate) {
        return new ResponseEntity<>(blogCreatePort.createBlog(blogCreate), HttpStatus.OK);
    }


    @GetMapping("/get-blogs")
    ResponseEntity<BlogDetailsResponse> getBlogs(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NO, required = false) int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
                                                 @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        BlogDetailsResponse blogDetailsResponse = blogDetailsPort.getBlogs(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(blogDetailsResponse, HttpStatus.OK);
    }


//    //    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("{id}")
//    ResponseEntity<BlogCreate> getBlogById(@PathVariable int id) {
//        return new ResponseEntity<>(blogPort.getBlogById(id), HttpStatus.OK);
//    }
//
//    @PutMapping("{id}")
////    @PreAuthorize("hasAuthority('blogCreate:update')")
//    ResponseEntity<BlogCreate> updateBlog(@PathVariable int id, @RequestBody BlogCreate blogCreate) {
//        return new ResponseEntity<>(blogPort.updateBlog(id, blogCreate), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
////    @PreAuthorize("hasAuthority('blog:delete')")
//    ResponseEntity<String> deleteBlog(@PathVariable int id) {
//        blogPort.deleteBlog(id);
//        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
//    }


}
