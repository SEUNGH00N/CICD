package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        postService.save(post);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        postService.update(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
