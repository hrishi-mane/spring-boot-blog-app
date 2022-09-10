package com.example.blogapp.repository;

import com.example.blogapp.domain.opinion.OpinionDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends JpaRepository<OpinionDomain, Integer> {
    Optional<List<OpinionDomain>> findByBlogId(int id);

}
