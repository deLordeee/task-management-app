package com.example.Task.Managment.repository;

import com.example.Task.Managment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User , Long> {
    User findByUsername(String username);
}
