package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {

    @EntityGraph(attributePaths = {"sections", "sections.tasks"})
    Optional<LearningPath> findById(Long pathId);
    List<LearningPath> findByUserId(Long userId);
}
