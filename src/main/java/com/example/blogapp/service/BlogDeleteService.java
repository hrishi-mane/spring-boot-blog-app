package com.example.blogapp.service;

import com.example.blogapp.config.BlogMessageConfig;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.model.blog.BlogDeleteRes;
import com.example.blogapp.model.blog.ResultStatus;
import com.example.blogapp.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Class for deleting the blog from the database.
 */
@Service
@Slf4j
public class BlogDeleteService implements BlogDelete {
    private final BlogRepository blogRepository;

    private final BlogMessageConfig blogMessageConfig;

    @Autowired
    public BlogDeleteService(BlogRepository blogRepository, BlogMessageConfig blogMessageConfig) {
        this.blogRepository = blogRepository;
        this.blogMessageConfig = blogMessageConfig;
    }


    /**
     * Method deletes a blog by its id if it is present in the database.
     * @param id The id of the blog to be deleted
     * @return BlogDeleteRes object containing the success message and deleted blog id.
     */
    @Override
    public BlogDeleteRes deleteBlog(int id) {
        try{
            //here we are checking if the blog to be deleted exists in the database or not.
            if (blogRepository.findById(id).isPresent()){
                blogRepository.deleteById(id);
            }
            else{
                log.info(String.format("getClass()%s%s%s", " ", "deleteBlog", blogMessageConfig.
                        getInvalidBlogIdMessage()));
                throw new BlogApiException(blogMessageConfig.getInvalidBlogIdMessage());
            }
        } catch (Exception e){
            log.info(String.format("getClass()%s%s%s", " ", "deleteBlog", Arrays.toString(e.getStackTrace())));
            throw new BlogApiException(e.getMessage());
        }
        return makeResponse(id);
    }

    /**
     * Method generates a success response.
     */
    private BlogDeleteRes makeResponse(int id) {
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setStatus(blogMessageConfig.getDeletionSuccessMessage());
        BlogDeleteRes blogDeleteRes = new BlogDeleteRes();
        blogDeleteRes.setId(id);
        blogDeleteRes.setResultStatus(resultStatus);

        return blogDeleteRes;
    }
}
