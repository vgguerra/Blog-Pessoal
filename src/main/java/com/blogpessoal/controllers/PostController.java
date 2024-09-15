package com.blogpessoal.controllers;

import com.blogpessoal.dtos.PostDTO;
import com.blogpessoal.models.Post;
import com.blogpessoal.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody PostDTO postDTO) throws Exception {
        Post newPost = this.postService.newPost(postDTO);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) throws Exception {
        this.postService.deletePost(id);
        return new ResponseEntity<>("Post delete successfully",HttpStatus.NO_CONTENT);
    }

}
