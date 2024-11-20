package com.example.Task.Managment.controller;


import com.example.Task.Managment.entity.Priority;
import com.example.Task.Managment.entity.Status;
import com.example.Task.Managment.entity.Subject;
import com.example.Task.Managment.entity.Task;
import com.example.Task.Managment.repository.TaskRepository;
import com.example.Task.Managment.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskWebController {
    private final TaskService taskService;

    private final TaskRepository taskRepository;

    public TaskWebController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }



    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.editTask(id, task);
        return "redirect:/tasks";
    }
    @GetMapping
    public String listTasks(
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Subject subject,
            Model model
    ) {
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("priorities", Priority.values());

        model.addAttribute("selectedPriority", priority);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedSubject", subject);

        List<Task> tasks = taskService.getFilteredTasks(priority, status, subject);
        model.addAttribute("tasks", tasks);

        return "task-list";
    }
}
