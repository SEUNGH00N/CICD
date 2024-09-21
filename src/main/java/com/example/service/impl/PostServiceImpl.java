package com.example.service.impl;

import com.example.dao.PostDao;
import com.example.model.Post;
import com.example.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostDao postDao;

    // 생성자 주입을 사용
    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void update(Post post) {
        postDao.update(post);
    }

    @Override
    public void delete(Long id) {
        postDao.delete(id);
    }

    @Override
    public Post findById(Long id) {
        return postDao.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }
}
