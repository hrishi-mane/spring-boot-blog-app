package com.example.blogapp.repository;

import com.example.blogapp.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
    Optional<List<Opinion>> findByBlogId(int id);

}
