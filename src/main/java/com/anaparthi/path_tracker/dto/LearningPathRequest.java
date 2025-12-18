package com.anaparthi.path_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class LearningPathRequest {

    @NotBlank
    private String title;

    private String description;
    private LocalDate startDate;
    private LocalDate targetEndDate;

    public LearningPathRequest() {
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
