package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.domain.TaskType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;
    private TaskType type;

    @Min(0)
    private Integer estimatedMinutes;

    private TaskStatus status;


}
