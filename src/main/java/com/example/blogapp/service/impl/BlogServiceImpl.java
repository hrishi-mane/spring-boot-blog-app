package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Blog;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.payload.BlogDto;
import com.example.blogapp.payload.Response;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    ModelMapper modelMapper;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog = modelMapper.map(blogDto, Blog.class);
        Blog response = blogRepository.save(blog);

        return modelMapper.map(response, BlogDto.class);
    }


    @Override
    public Response getBlogs(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Blog> blogPage = blogRepository.findAll(pageable);


        List<BlogDto> blogDtoList = blogPage.getContent().stream().
                map(blog -> modelMapper.map(blog, BlogDto.class)).toList();

        return toResponseObject(blogPage, blogDtoList);
    }


    @Override
    public BlogDto getBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Blog", "id", id));
        return modelMapper.map(blog, BlogDto.class);
    }

    @Override
    public BlogDto updateBlog(int id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Blog", "id", id));
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        Blog response = blogRepository.save(blog);

        return modelMapper.map(response, BlogDto.class);
    }

    @Override
    public void deleteBlog(int id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Blog", "id", id));
        blogRepository.delete(blog);
    }

    private Response toResponseObject(Page<Blog> blogPage, List<BlogDto> blogDtoList) {
        Response response = new Response();
        response.setBlogdtos(blogDtoList);
        response.setPageNo(blogPage.getNumber());
        response.setTotalPages(blogPage.getTotalPages());
        response.setTotalElements(blogPage.getNumberOfElements());
        response.setLast(blogPage.isLast());
        return response;
    }


}
