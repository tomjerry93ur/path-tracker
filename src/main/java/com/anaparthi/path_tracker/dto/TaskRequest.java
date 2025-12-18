package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.TaskType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TaskRequest {

    @NotBlank
    private String title;

    private String description;
    private TaskType type;

    @Min(0)
    private Integer estimatedMinutes;

    public TaskRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }
}
