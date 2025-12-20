package com.anaparthi.path_tracker.mapper;

import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.dto.TaskRequest;
import com.anaparthi.path_tracker.dto.TaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task mapToTask(TaskRequest request);

    TaskResponse mapToTaskResponse(Task task);
}