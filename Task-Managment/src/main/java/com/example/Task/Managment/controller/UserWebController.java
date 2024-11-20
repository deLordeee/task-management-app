package com.example.Task.Managment.controller;


import com.example.Task.Managment.entity.Task;
import com.example.Task.Managment.entity.User;
import com.example.Task.Managment.service.TaskService;
import com.example.Task.Managment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserWebController {
    private UserService userService;

    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/users/login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/tasks";
    }
}