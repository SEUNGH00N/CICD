package com.example.service;

import com.example.model.User;
import java.util.List;

public interface UserService {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
}
