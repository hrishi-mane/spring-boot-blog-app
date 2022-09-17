package com.example.blogapp.service;

import com.example.blogapp.domain.blog.blogcreate.BlogCreateDomain;
import com.example.blogapp.domain.opinion.OpinionDomain;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.exception.ResourceNotFound;
import com.example.blogapp.model.opinion.Opinion;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.OpinionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService implements OpinionPort {

    private final OpinionRepository opinionRepository;
    private final BlogRepository blogRepository;

    ModelMapper modelMapper;

    @Autowired
    public OpinionService(OpinionRepository opinionRepository, BlogRepository blogRepository, ModelMapper modelMapper) {
        this.opinionRepository = opinionRepository;
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Opinion createOpinion(int blog_id, Opinion opinionDto) {
        BlogCreateDomain blog = blogRepository.findById(blog_id).orElseThrow(() -> new ResourceNotFound("BlogCreate", "id", blog_id));
        OpinionDomain opinion = modelMapper.map(opinionDto, OpinionDomain.class);
        opinion.setBlog(blog);
        OpinionDomain response = opinionRepository.save(opinion);

        return modelMapper.map(response, Opinion.class);
    }

    @Override
    public List<Opinion> getOpinionsByBlogId(int blog_id) {
        List<OpinionDomain> opinions = opinionRepository.findByBlogId(blog_id).
                orElseThrow(() -> new ResourceNotFound("blog", "id", blog_id));
        return opinions.stream().map(opinion -> modelMapper.map(opinion, Opinion.class)).toList();
    }

    @Override
    public Opinion getOpinionById(int blog_id, int opinion_id) {
        OpinionDomain opinion = retrieveOpinion(blog_id, opinion_id);
        return modelMapper.map(opinion, Opinion.class);
    }

    @Override
    public Opinion updateOpinionById(int blog_id, int opinion_id, Opinion opinionDto) {
        OpinionDomain opinion = retrieveOpinion(blog_id, opinion_id);

        opinion.setName(opinionDto.getName());
        opinion.setEmail(opinionDto.getEmail());
        opinion.setMessage(opinionDto.getMessage());

        OpinionDomain response = opinionRepository.save(opinion);
        return modelMapper.map(response, Opinion.class);

    }

    @Override
    public void deleteOpinionById(int blog_id, int opinion_id) {
        OpinionDomain opinion = retrieveOpinion(blog_id, opinion_id);

        opinionRepository.delete(opinion);
    }

    private OpinionDomain retrieveOpinion(int blog_id, int opinion_id) {
        BlogCreateDomain blog = blogRepository.findById(blog_id).orElseThrow(() ->
                new ResourceNotFound("BlogCreate", "id", blog_id));
        OpinionDomain opinion = opinionRepository.findById(opinion_id).orElseThrow(() ->
                new ResourceNotFound("opinion", "id", opinion_id));

        if (opinion.getBlog().getId() != blog.getId()) {
            throw new BlogApiException("Comment with given id not found");
        }
        return opinion;
    }
}
