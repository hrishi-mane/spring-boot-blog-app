package com.example.blogapp.service;

import com.example.blogapp.payload.OpinionDto;

import java.util.List;

public interface OpinionService {
    OpinionDto createOpinion(int blog_id, OpinionDto opinionDto);

    List<OpinionDto> getOpinionsByBlogId(int blog_id);

    OpinionDto getOpinionById(int blog_id, int opinion_id);

    OpinionDto updateOpinionById(int blog_id, int opinion_id, OpinionDto opinionDto);

    void deleteOpinionById(int blog_id, int opinion_id);
}
