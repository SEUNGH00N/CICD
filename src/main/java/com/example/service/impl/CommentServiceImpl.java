package com.example.service.impl;

import com.example.model.Comment;
import com.example.dao.CommentDao;
import com.example.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void delete(Long id) {
        commentDao.delete(id);
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }
}
