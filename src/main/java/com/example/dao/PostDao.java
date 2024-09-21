package com.example.dao;

import com.example.model.Post;

import java.util.List;

public interface PostDao {
    void save(Post post);
    void update(Post post);
    void delete(Long id);
    Post findById(Long id);
    List<Post> findAll();
}
