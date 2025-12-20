package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
import com.anaparthi.path_tracker.mapper.TaskMapper;
import com.anaparthi.path_tracker.repository.SectionRepository;
import com.anaparthi.path_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SectionRepository sectionRepository;
    private final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public TaskResponse createTask(Long sectionId, TaskRequest request) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        Task task = taskMapper.mapToTask(request);
        task.setStatus(TaskStatus.NOT_STARTED);
        section.addTask(task);
        sectionRepository.save(section);
        return taskMapper.mapToTaskResponse(task);
    }

    public TaskResponse updateTask(Long pathId, Long sectionId, Long taskId, TaskRequest request) {

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setStatus(request.getStatus());
        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.mapToTaskResponse(updatedTask);
    }

    public void deleteTask(Long pathId, Long sectionId, Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }
}
