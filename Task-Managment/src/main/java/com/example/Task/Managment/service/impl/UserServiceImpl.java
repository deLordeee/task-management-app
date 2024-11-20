package com.example.Task.Managment.service.impl;

import com.example.Task.Managment.entity.User;
import com.example.Task.Managment.repository.UserRepository;
import com.example.Task.Managment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
     private final UserRepository userRepository ;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    public UserServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("User not found!"));
         userRepository.delete(user);
    }

    @Override
    public ResponseEntity<User> editUser(Long id , User newUser) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("User not found!"));
        if (newUser.getUsername() != null) {
            user.setUsername(newUser.getUsername());
        }
        if (newUser.getPassword() != null) {
            user.setPassword(newUser.getPassword());
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return user;
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public String verify(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "Fail";
    }


}
