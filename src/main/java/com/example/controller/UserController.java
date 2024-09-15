package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-users";
    }

    @GetMapping("/user/new")
    public String newUserForm() {
        return "new-user";
    }

    @PostMapping("/user/save")
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("email") String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.save(user);
        return "redirect:/users";
    }
}
