package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.repository.LearningPathRepository;
import com.anaparthi.path_tracker.repository.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final SectionRepository sectionRepository;

    public LearningPathService(LearningPathRepository learningPathRepository,
                               SectionRepository sectionRepository) {
        this.learningPathRepository = learningPathRepository;
        this.sectionRepository = sectionRepository;
    }

    public LearningPath createLearningPath(LearningPath path) {
        path.setStatus(LearningPathStatus.NOT_STARTED);
        return learningPathRepository.save(path);
    }

    public List<LearningPath> getAllLearningPaths() {
        return learningPathRepository.findAll();
    }

    public LearningPath getLearningPathById(Long pathId) {
        return learningPathRepository.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));
    }

    public LearningPath updateLearningPath(Long pathId, LearningPath newLearningPath){
        LearningPath path =  learningPathRepository.findById(pathId).orElseThrow(() ->new RuntimeException("Learning Path Not Found"));
        path.setTitle(newLearningPath.getTitle());
        path.setDescription(newLearningPath.getDescription());
        path.setStartDate(newLearningPath.getStartDate());
        path.setTargetEndDate(newLearningPath.getTargetEndDate());

        return learningPathRepository.save(path);
    }

    public LearningPath startLearningPath(Long pathId) {

        learningPathRepository.findByStatus(LearningPathStatus.IN_PROGRESS)
                .ifPresent(path -> {
                    throw new RuntimeException("Another learning path is already in progress");
                });
        LearningPath path = getLearningPathById(pathId);
        path.setStatus(LearningPathStatus.IN_PROGRESS);

        return learningPathRepository.save(path);
    }
    public void CompletePath(Long pathId) {

        long incompleteSections =
                sectionRepository.countByLearningPathIdAndStatusNot(
                        pathId, SectionStatus.COMPLETED);

        if (incompleteSections == 0) {
            LearningPath path = getLearningPathById(pathId);
            path.setStatus(LearningPathStatus.COMPLETED);
            learningPathRepository.save(path);
        }
    }
}