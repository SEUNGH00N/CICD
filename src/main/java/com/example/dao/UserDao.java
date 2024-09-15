package com.example.dao;

import com.example.model.User;
import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
}
