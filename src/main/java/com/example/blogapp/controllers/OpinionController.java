package com.example.blogapp.controllers;

import com.example.blogapp.payload.OpinionDto;
import com.example.blogapp.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/blogs/{blog_id}/opinions")
public class OpinionController {

    OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @PostMapping
    public ResponseEntity<OpinionDto> createOpinion(@PathVariable int blog_id, @RequestBody OpinionDto opinionDto){
        return new ResponseEntity<>(opinionService.createOpinion(blog_id,opinionDto), HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<List<OpinionDto>> getOpinionsByBlogId(@PathVariable int blog_id){
        return new ResponseEntity<>(opinionService.getOpinionsByBlogId(blog_id), HttpStatus.OK);
    }

    @GetMapping("{opinion_id}")
    public ResponseEntity<OpinionDto> getOpinionById(@PathVariable int blog_id, @PathVariable int opinion_id){
        return new ResponseEntity<>(opinionService.getOpinionById(blog_id,opinion_id), HttpStatus.OK);
    }

    @PutMapping("{opinion_id}")
    public ResponseEntity<OpinionDto> updateOpinionById(@RequestBody OpinionDto opinionDto,
                                                        @PathVariable int blog_id,
                                                        @PathVariable int opinion_id){
        return new ResponseEntity<>(opinionService.updateOpinionById(blog_id,opinion_id,opinionDto), HttpStatus.OK);
    }

    @DeleteMapping("{opinion_id}")
    public ResponseEntity<String> deleteOpinionById(@PathVariable int blog_id, @PathVariable int opinion_id){
        opinionService.deleteOpinionById(blog_id,opinion_id);
        return new ResponseEntity<>("Opinion deleted successfully", HttpStatus.OK);
    }


}
