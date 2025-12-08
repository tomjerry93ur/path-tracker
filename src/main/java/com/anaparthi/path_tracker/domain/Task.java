package com.anaparthi.path_tracker.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Task title cannot be empty")
    @Size(min = 3, max = 100, message = "Task title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    @Positive(message = "Estimated minutes must be greater than 0")
    @Column(name = "estimated_minutes")
    private int estimatedMinutes;

    @Positive(message = "Actual minutes must be greater than 0")
    @Column(name = "actual_minutes")
    private Integer actualMinutes;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType status;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    public Task() {}

    public Task(Long id, String title, String description, TaskType type,
                int estimatedMinutes, Integer actualMinutes,
                LocalDate completionDate, TaskType status, Section section) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.estimatedMinutes = estimatedMinutes;
        this.actualMinutes = actualMinutes;
        this.completionDate = completionDate;
        this.status = status;
        this.section = section;
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

    public TaskType getType() {
        return type;
    }
    public void setType(TaskType type) {
        this.type = type;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }
    public void setEstimatedMinutes(int estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public Integer getActualMinutes() {
        return actualMinutes;
    }
    public void setActualMinutes(Integer actualMinutes) {
        this.actualMinutes = actualMinutes;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public TaskType getStatus() {
        return status;
    }
    public void setStatus(TaskType status) {
        this.status = status;
    }

    public Section getSection() {
        return section;
    }
    public void setSection(Section section) {
        this.section = section;
    }
}
