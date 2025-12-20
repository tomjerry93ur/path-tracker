package com.anaparthi.path_tracker.repository;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.Task;
import com.anaparthi.path_tracker.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
