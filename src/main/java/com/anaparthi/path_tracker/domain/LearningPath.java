package com.anaparthi.path_tracker.domain;

import com.anaparthi.path_tracker.auth.User;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "learning_paths")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningPath {

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

    @OneToMany(
            mappedBy = "path",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    public void addSection(Section section) {
        sections.add(section);
        section.setPath(this);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.setPath(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
