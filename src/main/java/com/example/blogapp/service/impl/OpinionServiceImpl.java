package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Blog;
import com.example.blogapp.entity.Opinion;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.payload.OpinionDto;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.OpinionRepository;
import com.example.blogapp.service.OpinionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final BlogRepository blogRepository;

    ModelMapper modelMapper;

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository, BlogRepository blogRepository, ModelMapper modelMapper) {
        this.opinionRepository = opinionRepository;
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OpinionDto createOpinion(int blog_id, OpinionDto opinionDto) {
        Blog blog = blogRepository.findById(blog_id).orElseThrow(() ->
                new ResourceNotFound("Blog", "id", blog_id));
        Opinion opinion = modelMapper.map(opinionDto, Opinion.class);
        opinion.setBlog(blog);
        Opinion response = opinionRepository.save(opinion);

        return modelMapper.map(response, OpinionDto.class);
    }

    @Override
    public List<OpinionDto> getOpinionsByBlogId(int blog_id) {
        List<Opinion> opinions = opinionRepository.findByBlogId(blog_id).
                orElseThrow(() -> new ResourceNotFound("blog", "id", blog_id));
        return opinions.stream().map(opinion -> modelMapper.map(opinion, OpinionDto.class)).toList();
    }

    @Override
    public OpinionDto getOpinionById(int blog_id, int opinion_id) {
        Opinion opinion = retrieveOpinion(blog_id, opinion_id);;
        return modelMapper.map(opinion, OpinionDto.class);
    }

    @Override
    public OpinionDto updateOpinionById(int blog_id, int opinion_id, OpinionDto opinionDto) {
        Opinion opinion = retrieveOpinion(blog_id, opinion_id);

        opinion.setName(opinionDto.getName());
        opinion.setEmail(opinionDto.getEmail());
        opinion.setMessage(opinionDto.getMessage());

        Opinion response = opinionRepository.save(opinion);
        return modelMapper.map(response, OpinionDto.class);

    }

    @Override
    public void deleteOpinionById(int blog_id, int opinion_id) {
        Opinion opinion = retrieveOpinion(blog_id, opinion_id);

        opinionRepository.delete(opinion);
    }

    private Opinion retrieveOpinion(int blog_id, int opinion_id) {
        Blog blog = blogRepository.findById(blog_id).orElseThrow(() ->
                new ResourceNotFound("Blog", "id", blog_id));
        Opinion opinion = opinionRepository.findById(opinion_id).orElseThrow(() ->
                new ResourceNotFound("opinion", "id", opinion_id ));

        if(opinion.getBlog().getId() != blog.getId()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment with given id not found");
        }
        return opinion;
    }
}
