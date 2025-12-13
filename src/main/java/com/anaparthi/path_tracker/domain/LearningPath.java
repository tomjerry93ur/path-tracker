package com.anaparthi.path_tracker.domain;

import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "learning_paths")
@Getter
@Setter
public class LearningPath{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column
    private String description;

    @NotNull(message = "Start date cannot be null")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Target end date cannot be null")
    @Column(name = "target_end_date", nullable = false)
    private LocalDate targetEndDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LearningPathStatus status;

    public LearningPath() {}

    public LearningPath(Long id,String title,String description,LocalDate startDate,LocalDate targetEndDate,LearningPathStatus status,List<Section> sections) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetEndDate = targetEndDate;
        this.status = status;
    }
}
