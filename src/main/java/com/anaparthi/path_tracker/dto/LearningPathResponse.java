package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.LearningPathStatus;
import java.time.LocalDate;

public class LearningPathResponse {

    private Long id;
    private String title;
    private String description;
    private LearningPathStatus status;
    private LocalDate startDate;
    private LocalDate targetEndDate;

    public LearningPathResponse() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LearningPathStatus getStatus() {
        return status;
    }

    public void setStatus(LearningPathStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getTargetEndDate() {
        return targetEndDate;
    }

    public void setTargetEndDate(LocalDate targetEndDate) {
        this.targetEndDate = targetEndDate;
    }
}
