package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.TaskService;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/paths/{pathId}/sections/{sectionId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@PathVariable Long pathId, @PathVariable Long sectionId, @Valid @RequestBody TaskRequest request) {
        return taskService.createTask(sectionId, request);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(
            @PathVariable Long pathId,
            @PathVariable Long sectionId,
            @PathVariable Long taskId,
            @Valid @RequestBody TaskRequest request
    ) {
        return taskService.updateTask(pathId, sectionId, taskId, request);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long pathId,
            @PathVariable Long sectionId,
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(pathId, sectionId, taskId);
        return ResponseEntity.noContent().build();
    }
}
