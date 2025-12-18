package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
import com.anaparthi.path_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SectionService sectionService;



}
