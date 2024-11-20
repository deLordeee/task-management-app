package com.example.Task.Managment.service.impl;

import com.example.Task.Managment.entity.*;
import com.example.Task.Managment.repository.TaskRepository;
import com.example.Task.Managment.service.TaskService;
import com.example.Task.Managment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;

    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public Task checkStatus(Task task){
        if(!task.getStartDate().truncatedTo(ChronoUnit.DAYS).equals(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))){
            task.setStatus(Status.PLANNED);
            task.setStartDate(task.getStartDate());
            task.setEndDate(task.getEndDate());
        }
        else{
            task.setStatus(Status.ACTIVE);
            task.setEndDate(task.getEndDate());
        }
            return taskRepository.save(task);
    }
    public List<Task> sortByStatus(Status status){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        return taskRepository.findByStatus(status);
    }
    public List<Task> sortByPriority(Priority priority) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        return taskRepository.findByUserAndPriority(user, priority);
    }
    @Override
    public List<Task> getFilteredTasks(Priority priority, Status status, Subject subject) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        return taskRepository.findByFilters(user, priority, status, subject);
    }
    @Override
    public Task createTask(Task task) {
        System.out.println("Status: "+ task.getStatus());
        System.out.println("Start date: "+ task.getStartDate());
        System.out.println("End date: "+ task.getEndDate());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        task.setUser(user);

        checkStatus(task);



        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        // Get currently authenticated username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        // Return only tasks for the current user
        return taskRepository.findByUser(user);
    }

    @Override
    public Task completeTask(Long task_id) {
        Task task = taskRepository
                .findById(task_id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        task.setStatus(Status.DONE);
        return taskRepository.save(task);
    }

    @Override
    public ResponseEntity<Task> deleteTask(Long task_id) {
        Task task = taskRepository
                .findById(task_id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
         taskRepository.delete(task);
        return null;
    }

    @Override
    public ResponseEntity<Task> editTask(Long id , Task task) {
        Task oldTask = taskRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        Optional.ofNullable(task.getTitle()).ifPresent(oldTask::setTitle);
        Optional.ofNullable(task.getSubject()).ifPresent(oldTask::setSubject);
        Optional.ofNullable(task.getTaskText()).ifPresent(oldTask::setTaskText);
        Optional.ofNullable(task.getStartDate()).ifPresent(oldTask::setStartDate);
        Optional.ofNullable(task.getEndDate()).ifPresent(oldTask::setEndDate);
        Optional.ofNullable(task.getPriority()).ifPresent(oldTask::setPriority);



        checkStatus(oldTask);
        Task savedTask = taskRepository.save(oldTask);


        return ResponseEntity.ok(savedTask);
    }


}
