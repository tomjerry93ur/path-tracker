package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
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

    public SectionResponse addSection(Long pathId, SectionRequest request) {

        learningPathService.getLearningPathById(pathId);

        Long count = sectionRepository.countByLearningPathId(pathId);

        Section section = new Section();
        section.setTitle(request.getTitle());
        section.setDescription(request.getDescription());
        section.setEstimatedDays(request.getEstimatedDays());
        section.setLearningPathId(pathId);
        section.setStatus(SectionStatus.NOT_STARTED);
        section.setOrderIndex(count != null ? count + 1 : 1);
        Section saved = sectionRepository.save(section);
        return Response(saved);
    }

    public List<SectionResponse> getSectionsById(Long pathId) {

        learningPathService.getLearningPathById(pathId);

        return  sectionRepository
                .findByLearningPathIdOrderByOrderIndexAsc(pathId)
                .stream()
                .map(this::Response)
                .toList();
    }

    public SectionResponse updateSection(Long sectionId, SectionRequest request) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        section.setTitle(request.getTitle());
        section.setDescription(request.getDescription());
        section.setEstimatedDays(request.getEstimatedDays());

        Section updated = sectionRepository.save(section);
        return Response(updated);
    }

    public void CompleteSection(Long sectionId) {

        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        long incompleteTasks =
                taskRepository.countBySectionIdAndStatusNot(
                        section.getId(), TaskStatus.COMPLETED);

        if (incompleteTasks == 0) {
            section.setStatus(SectionStatus.COMPLETED);
            sectionRepository.save(section);

            learningPathService.CompletePath(section.getLearningPathId());
        }
    }

    private SectionResponse Response(Section section) {

        SectionResponse response = new SectionResponse();
        response.setId(section.getId());
        response.setTitle(section.getTitle());
        response.setDescription(section.getDescription());
        response.setEstimatedDays(section.getEstimatedDays());
        response.setStatus(section.getStatus());
        response.setOrderIndex(section.getOrderIndex());

        return response;
    }
}
