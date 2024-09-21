package com.example.dao.impl;

import com.example.dao.CommentDao;
import com.example.model.Comment;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void update(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = findById(id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public Comment findById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }
}
