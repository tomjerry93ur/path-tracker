package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.repository.LearningPathRepository;
import com.anaparthi.path_tracker.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final SectionRepository sectionRepository;

    public LearningPathService(LearningPathRepository learningPathRepository,
                               SectionRepository sectionRepository) {
        this.learningPathRepository = learningPathRepository;
        this.sectionRepository = sectionRepository;
    }

    public LearningPathResponse createLearningPath(LearningPathRequest request) {

        LearningPath path = new LearningPath();
        path.setTitle(request.getTitle());
        path.setDescription(request.getDescription());
        path.setStartDate(request.getStartDate());
        path.setTargetEndDate(request.getTargetEndDate());
        path.setStatus(LearningPathStatus.NOT_STARTED);

        LearningPath saved = learningPathRepository.save(path);

        return Response(saved);
    }

    public List<LearningPathResponse> getAllLearningPaths() {

        return learningPathRepository.findAll()
                .stream()
                .map(this::Response)
                .collect(Collectors.toList());
    }

    public LearningPath getLearningPathById(Long pathId) {
        return learningPathRepository.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));
    }
    public LearningPathResponse updateLearningPath(Long pathId,
                                                   LearningPathRequest request) {

        LearningPath path = getLearningPathById(pathId);

        path.setTitle(request.getTitle());
        path.setDescription(request.getDescription());
        path.setStartDate(request.getStartDate());
        path.setTargetEndDate(request.getTargetEndDate());

        LearningPath saved = learningPathRepository.save(path);

        return Response(saved);
    }

    public LearningPathResponse startLearningPath(Long pathId) {

        learningPathRepository.findByStatus(LearningPathStatus.IN_PROGRESS)
                .ifPresent(p -> {
                    throw new RuntimeException("Another learning path is already in progress");
                });

        LearningPath path = getLearningPathById(pathId);
        path.setStatus(LearningPathStatus.IN_PROGRESS);

        LearningPath saved = learningPathRepository.save(path);

        return Response(saved);
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
    private LearningPathResponse Response(LearningPath path) {

        LearningPathResponse response = new LearningPathResponse();
        response.setId(path.getId());
        response.setTitle(path.getTitle());
        response.setDescription(path.getDescription());
        response.setStatus(path.getStatus());
        response.setStartDate(path.getStartDate());
        response.setTargetEndDate(path.getTargetEndDate());

        return response;
    }
}
