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

    @NotBlank(message = "Section title cannot be empty")
    @Size(min = 3, max = 100, message = "Section title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Positive(message = "Estimated duration must be greater than 0")
    @Column(name = "estimated_days")
    private int estimatedDays;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SectionStatus status;

    @ManyToOne
    @JoinColumn(name = "learning_path_id")
    private LearningPath learningPath;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Section() {}

    public Section(Long id, String title, String description, int estimatedDays,
                   LocalDate startDate, LocalDate endDate, SectionStatus status,
                   LearningPath learningPath, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedDays = estimatedDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.learningPath = learningPath;
        this.tasks = tasks;
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

    public LearningPath getLearningPath() {
        return learningPath;
    }
    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

