package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
import com.anaparthi.path_tracker.repository.SectionRepository;
import com.anaparthi.path_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final TaskRepository taskRepository;

}
