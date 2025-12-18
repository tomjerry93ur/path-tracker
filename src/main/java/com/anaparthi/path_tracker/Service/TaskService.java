package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
import com.anaparthi.path_tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SectionService sectionService;

    public TaskService(TaskRepository taskRepository,
                       SectionService sectionService) {
        this.taskRepository = taskRepository;
        this.sectionService = sectionService;
    }

    public TaskResponse addTask(Long sectionId, TaskRequest request) {

        sectionService.getSectionsById(sectionId);

        Task task = new Task();
        task.setSectionId(sectionId);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setType(request.getType());
        task.setEstimatedMinutes(request.getEstimatedMinutes());
        task.setStatus(TaskStatus.NOT_STARTED);

        return Response(taskRepository.save(task));
    }

    public List<TaskResponse> getTasksBySection(Long sectionId) {

        sectionService.getSectionsById(sectionId);

        return taskRepository.findBySectionId(sectionId)
                .stream()
                .map(this::Response)
                .toList();
    }

    public TaskResponse updateTask(Long taskId, TaskRequest request) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setType(request.getType());
        task.setEstimatedMinutes(request.getEstimatedMinutes());

        return Response(taskRepository.save(task));
    }

    public TaskResponse updateTaskStatus(Long taskId, TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        if (status == TaskStatus.COMPLETED) {
            task.setCompletedAt(LocalDateTime.now());
        }

        taskRepository.save(task);

        sectionService.CompleteSection(task.getSectionId());

        return Response(task);
    }

    private TaskResponse Response(Task task) {

        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setTitle(task.getTitle());
        response.setType(task.getType());
        response.setEstimatedMinutes(task.getEstimatedMinutes());
        response.setStatus(task.getStatus());
        response.setCompletedAt(task.getCompletedAt());

        return response;
    }
}
