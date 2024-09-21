package com.example.dao.impl;

import com.example.dao.PostDao;
import com.example.model.Post;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Post post) {
        entityManager.persist(post);
    }

    @Override
    public void update(Post post) {
        entityManager.merge(post);
    }

    @Override
    public void delete(Long id) {
        Post post = findById(id);
        if (post != null) {
            entityManager.remove(post);
        }
    }

    @Override
    public Post findById(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }
}
