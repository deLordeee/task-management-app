package com.example.Task.Managment.service;

import com.example.Task.Managment.entity.Priority;
import com.example.Task.Managment.entity.Status;
import com.example.Task.Managment.entity.Subject;
import com.example.Task.Managment.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    List<Task> getAllTasks();

    Task completeTask(Long task_id);

    ResponseEntity<Task> deleteTask(Long task_id);

    ResponseEntity<Task> editTask(Long id , Task task);
     Task checkStatus(Task task);

    List<Task> sortByPriority(Priority priority);
    List<Task> sortByStatus(Status status);

    List<Task> getFilteredTasks(Priority priority, Status status, Subject subject);
}
