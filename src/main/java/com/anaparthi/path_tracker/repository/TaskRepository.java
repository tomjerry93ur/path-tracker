package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findBySectionId(Long sectionId);

    long countBySectionIdAndStatusNot(
            Long sectionId,
            TaskStatus status
    );
}
