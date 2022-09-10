package com.example.blogapp.service;

import com.example.blogapp.model.opinion.Opinion;

import java.util.List;

public interface OpinionPort {
    Opinion createOpinion(int blog_id, Opinion opinion);

    List<Opinion> getOpinionsByBlogId(int blog_id);

    Opinion getOpinionById(int blog_id, int opinion_id);

    Opinion updateOpinionById(int blog_id, int opinion_id, Opinion opinion);

    void deleteOpinionById(int blog_id, int opinion_id);
}
