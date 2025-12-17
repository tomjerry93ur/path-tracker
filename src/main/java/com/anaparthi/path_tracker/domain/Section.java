package com.anaparthi.path_tracker.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long learningPathId;

    @NotBlank(message = "Section title cannot be empty")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String title;

    @Size(max = 500)
    private String description;

    @Positive
    @Column(name = "estimated_days")
    private int estimatedDays;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionStatus status;

    private Long orderIndex;

    public Section() {}

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLearningPathId() {
        return learningPathId;
    }

    public void setLearningPathId(Long learningPathId) {
        this.learningPathId = learningPathId;
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

    public int getEstimatedDays() {
        return estimatedDays;
    }

    public void setEstimatedDays(int estimatedDays) {
        this.estimatedDays = estimatedDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SectionStatus getStatus() {
        return status;
    }

    public void setStatus(SectionStatus status) {
        this.status = status;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }
}