package com.example.Task.Managment.controller;

import com.example.Task.Managment.entity.User;
import com.example.Task.Managment.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


   private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public  void deleteUser(@PathVariable  Long id){
        userService.deleteUser(id);

    }

    @PutMapping("editUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id , @RequestBody User user){

        return userService.editUser(id , user);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = userService.verify(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
