package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
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

    public Task addTask(Long sectionId, Task task) {

        sectionService.getSectionById(sectionId);

        task.setSectionId(sectionId);
        task.setStatus(TaskStatus.NOT_STARTED);

        return taskRepository.save(task);
    }

    public List<Task> getTasksBySection(Long sectionId) {
        sectionService.getSectionById(sectionId);
        return taskRepository.findBySectionId(sectionId);
    }

    public Task updateTask(Long taskId, Task updated) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setType(updated.getType());
        task.setEstimatedMinutes(updated.getEstimatedMinutes());

        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);

        if (status == TaskStatus.COMPLETED) {
            task.setCompletedAt(LocalDateTime.now());
        }
        taskRepository.save(task);

        sectionService.autoCompleteSection(
                task.getSectionId());

        return task;
    }
}
