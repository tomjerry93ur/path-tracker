package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.domain.TaskType;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;
    private String title;
    private TaskType type;
    private TaskStatus status;
    private Integer estimatedMinutes;

}
