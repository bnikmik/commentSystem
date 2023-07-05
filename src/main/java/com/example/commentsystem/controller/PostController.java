package com.example.commentsystem.controller;

import com.example.commentsystem.dto.PostDTO;
import com.example.commentsystem.dto.UserPostDTO;
import com.example.commentsystem.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PostController{
    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/1")
    public ResponseEntity<Collection<UserPostDTO>> getTopByCountComments() {
        return ResponseEntity.ok(service.getTopByCountComments());
    }

    @GetMapping("/2")
    ResponseEntity<Collection<PostDTO>> getAllPostWithComments() {
        return ResponseEntity.ok(service.getAllPostWithComments());
    }

    @GetMapping("/3")
    ResponseEntity<Collection<PostDTO>> getAllWithPageable(Integer pageNumber, Integer pageSize) {
        return ResponseEntity.ok(service.getAllWithPageable(pageNumber,pageSize));
    }

    @GetMapping("/4")
    ResponseEntity<Collection<PostDTO>> getPostsWithSpecification(@RequestParam String body) {
        return ResponseEntity.ok(service.getPostsWithSpecification(body));
    }
}
