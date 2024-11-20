package com.example.Task.Managment.controller;

import com.example.Task.Managment.entity.Priority;
import com.example.Task.Managment.entity.Task;
import com.example.Task.Managment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.createTask(task) , HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return  ResponseEntity.ok(tasks);
    }
    @PutMapping("/completeTask/{id}")
    public ResponseEntity<Task> completeTask(@PathVariable Long id){
        Task task = taskService.completeTask(id);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
    @PutMapping("/editTask/{id}")
    public ResponseEntity<Task> editTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.editTask(id, task);
    }
    @GetMapping("/sortByPriority")
    public List<Task> sortByPriority(@RequestBody Priority priority) {
       return taskService.sortByPriority(priority);
    }

}
