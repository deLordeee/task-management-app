package com.example.Task.Managment.service;

import com.example.Task.Managment.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    User createUser(User user);

    void deleteUser(Long id);

    ResponseEntity<User> editUser(Long id, User newUser);

    List<User> getAllUsers();

    User getUserById(Long id);

    User findUserByUsername(String username);

    String verify(User user);
}