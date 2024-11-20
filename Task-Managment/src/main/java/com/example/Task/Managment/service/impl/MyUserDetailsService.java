package com.example.Task.Managment.service.impl;


import com.example.Task.Managment.entity.MyUserDetails;
import com.example.Task.Managment.entity.User;
import com.example.Task.Managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        // Add logging for authentication flow tracking
        System.out.println("Found user: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        MyUserDetails userDetails = new MyUserDetails(user);

        // Add additional logging for authorities
        System.out.println("User authorities: " + userDetails.getAuthorities());

        return userDetails;
    }
}