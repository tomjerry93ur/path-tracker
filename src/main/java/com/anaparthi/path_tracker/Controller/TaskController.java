package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.TaskService;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
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
    public TaskResponse addTask(@PathVariable Long sectionId,
                                @Valid @RequestBody TaskRequest request) {
        return taskService.addTask(sectionId, request);
    }

    @GetMapping("/sections/{sectionId}/tasks")
    public List<TaskResponse> getTasks(@PathVariable Long sectionId) {
        return taskService.getTasksBySection(sectionId);
    }

    @PutMapping("/tasks/{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId,
                           @Valid @RequestBody TaskRequest request) {
        return taskService.updateTask(taskId, request);
    }
    @PostMapping("/tasks/{taskId}/status")
    public TaskResponse updateTaskStatus(@PathVariable Long taskId,
                                 @RequestParam TaskStatus status) {
        return taskService.updateTaskStatus(taskId, status);
    }
}
