package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.repository.SectionRepository;
import com.anaparthi.path_tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final TaskRepository taskRepository;
    private final LearningPathService learningPathService;

    public SectionService(SectionRepository sectionRepository,
                          TaskRepository taskRepository,
                          LearningPathService learningPathService) {
        this.sectionRepository = sectionRepository;
        this.taskRepository = taskRepository;
        this.learningPathService = learningPathService;
    }

    public Section addSection(Long pathId, Section section) {

        learningPathService.getLearningPathById(pathId);

        section.setLearningPathId(pathId);
        section.setStatus(SectionStatus.NOT_STARTED);

        return sectionRepository.save(section);
    }

    public List<Section> getSectionsByPath(Long pathId) {

        learningPathService.getLearningPathById(pathId);

        return sectionRepository
                .findByLearningPathIdOrderByOrderIndexAsc(pathId);
    }

    public Section updateSection(Long sectionId, Section updated) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        section.setTitle(updated.getTitle());
        section.setDescription(updated.getDescription());
        section.setEstimatedDays(updated.getEstimatedDays());

        return sectionRepository.save(section);
    }

    public void autoCompleteSection(Long sectionId) {

        Section section = getSectionById(sectionId);

        long incompleteTasks =
                taskRepository.countBySectionIdAndStatusNot(
                        section.getId(), TaskStatus.COMPLETED);

        if (incompleteTasks == 0) {

            section.setStatus(SectionStatus.COMPLETED);
            sectionRepository.save(section);

            learningPathService.autoCompletePath(
                    section.getLearningPathId());
        }
    }

    public Section getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
    }
}
