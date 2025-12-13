package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByLearningPathIdOrderByOrderIndexAsc(Long learningPathId);

    long countByLearningPathIdAndStatusNot(
            Long learningPathId,
            SectionStatus status);

    Long countByLearningPathId(Long pathId);
}