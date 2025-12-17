package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.TaskService;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/sections/{sectionId}/tasks")
    public Task addTask(@PathVariable Long sectionId,
                        @Valid @RequestBody Task task) {
        return taskService.addTask(sectionId, task);
    }

    @GetMapping("/sections/{sectionId}/tasks")
    public List<Task> getTasks(@PathVariable Long sectionId) {
        return taskService.getTasksBySection(sectionId);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId,
                           @Valid @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }
    @PostMapping("/tasks/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId,
                                 @RequestParam TaskStatus status) {
        return taskService.updateTaskStatus(taskId, status);
    }
}
