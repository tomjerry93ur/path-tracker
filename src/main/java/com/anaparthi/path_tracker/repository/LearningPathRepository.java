package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {

    @EntityGraph(attributePaths = {"sections", "sections.tasks"})
    Optional<LearningPath> findById(Long pathId);
}
