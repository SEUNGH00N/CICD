package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getApprovedUsers() {
        List<User> users = userRepository.findByAdmin("approved");
        System.out.println("Retrieved users: " + users); // 로그 출력
        return users;
    }
}
