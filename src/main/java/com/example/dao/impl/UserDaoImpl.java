package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public User findById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User", User.class).list();
        session.close();
        return users;
    }
}
