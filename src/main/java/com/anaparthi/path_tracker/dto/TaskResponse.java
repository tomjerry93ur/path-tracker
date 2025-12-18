package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.domain.TaskType;

import java.time.LocalDateTime;

public class TaskResponse {

    private Long id;
    private String title;
    private TaskType type;
    private TaskStatus status;
    private Integer estimatedMinutes;

    public TaskResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
    }
}
