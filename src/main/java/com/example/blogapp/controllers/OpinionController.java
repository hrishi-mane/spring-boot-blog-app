package com.example.blogapp.controllers;

import com.example.blogapp.model.opinion.Opinion;
import com.example.blogapp.service.OpinionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs/{blog_id}/opinions")
public class OpinionController {

    OpinionPort opinionPort;

    @Autowired
    public OpinionController(OpinionPort opinionPort) {
        this.opinionPort = opinionPort;
    }

    @PostMapping
    public ResponseEntity<Opinion> createOpinion(@PathVariable int blog_id, @RequestBody Opinion opinion) {
        return new ResponseEntity<>(opinionPort.createOpinion(blog_id, opinion), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Opinion>> getOpinionsByBlogId(@PathVariable int blog_id) {
        return new ResponseEntity<>(opinionPort.getOpinionsByBlogId(blog_id), HttpStatus.OK);
    }

    @GetMapping("{opinion_id}")
    public ResponseEntity<Opinion> getOpinionById(@PathVariable int blog_id, @PathVariable int opinion_id) {
        return new ResponseEntity<>(opinionPort.getOpinionById(blog_id, opinion_id), HttpStatus.OK);
    }

    @PutMapping("{opinion_id}")
    public ResponseEntity<Opinion> updateOpinionById(@RequestBody Opinion opinion,
                                                     @PathVariable int blog_id,
                                                     @PathVariable int opinion_id) {
        return new ResponseEntity<>(opinionPort.updateOpinionById(blog_id, opinion_id, opinion), HttpStatus.OK);
    }

    @DeleteMapping("{opinion_id}")
    public ResponseEntity<String> deleteOpinionById(@PathVariable int blog_id, @PathVariable int opinion_id){
        opinionPort.deleteOpinionById(blog_id, opinion_id);
        return new ResponseEntity<>("Opinion deleted successfully", HttpStatus.OK);
    }


}
