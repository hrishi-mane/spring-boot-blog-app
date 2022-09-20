package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.Blog;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.model.blogcreate.BlogCreate;
import com.example.blogapp.model.blogdetails.BlogDetailsResponse;
import com.example.blogapp.model.blogdetails.BlogResponse;
import com.example.blogapp.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements BlogPort {
    private final BlogRepository blogRepository;
    ModelMapper modelMapper;

    @Autowired
    public BlogService(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BlogCreate createBlog(BlogCreate blogCreate) {
        Blog blog = modelMapper.map(blogCreate, Blog.class);
        Blog response = blogRepository.save(blog);

        return modelMapper.map(response, BlogCreate.class);
    }


    @Override
    public BlogDetailsResponse getBlogs(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Blog> blogPage = blogRepository.findAll(pageable);


        List<BlogResponse> blogResponses = blogPage.getContent().stream().
                map(blog -> modelMapper.map(blog, BlogResponse.class)).toList();

        return toResponseObject(blogPage, blogResponses);
    }


    @Override
    public BlogCreate getBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("BlogCreate", "id", id));
        return modelMapper.map(blog, BlogCreate.class);
    }

    @Override
    public BlogCreate updateBlog(int id, BlogCreate blogCreateDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("BlogCreate", "id", id));
        blog.setTitle(blogCreateDto.getTitle());
        blog.setContent(blogCreateDto.getContent());
        Blog response = blogRepository.save(blog);

        return modelMapper.map(response, BlogCreate.class);
    }

    @Override
    public void deleteBlog(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("BlogCreate", "id", id));
        blogRepository.delete(blog);
    }

    private BlogDetailsResponse toResponseObject(Page<Blog> blogPage, List<BlogResponse> blogResponses) {
        BlogDetailsResponse blogDetailsResponse = new BlogDetailsResponse();
        blogDetailsResponse.setBlogResponses(blogResponses);
        blogDetailsResponse.setPageNo(blogPage.getNumber());
        blogDetailsResponse.setTotalPages(blogPage.getTotalPages());
        blogDetailsResponse.setTotalElements(blogPage.getNumberOfElements());
        blogDetailsResponse.setLast(blogPage.isLast());
        return blogDetailsResponse;
    }


}
