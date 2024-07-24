package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/approved")
    public List<User> getApprovedUsers() {
        List<User> approvedUsers = userService.getApprovedUsers();
        System.out.println("Approved users: " + approvedUsers); // 로그 출력
        return approvedUsers;
    }
}
