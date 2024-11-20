package com.example.Task.Managment.repository;

import com.example.Task.Managment.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task , Long> {

    List<Task> findByUser(User user);
    List<Task> findByStatus(Status status);
    @Query("SELECT t FROM Task t WHERE t.user = :user " +
            "AND (:priority is null OR t.priority = :priority) " +
            "AND (:status is null OR t.status = :status) " +
            "AND (:subject is null OR t.subject = :subject)")
    List<Task> findByFilters(
            @Param("user") User user,
            @Param("priority") Priority priority,
            @Param("status") Status status,
            @Param("subject") Subject subject
    );

    List<Task> findByUserAndPriority(User user, Priority priority);
}
