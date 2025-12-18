package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.mapper.LearningPathMapper;
import com.anaparthi.path_tracker.mapper.LearningPathMapperImpl;
import com.anaparthi.path_tracker.repository.LearningPathRepository;
import com.anaparthi.path_tracker.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final SectionRepository sectionRepository;
    private final SectionService sectionService;
    private final LearningPathMapper learningPathMapper = Mappers.getMapper(LearningPathMapper.class);


    public LearningPathResponse createLearningPath(LearningPathRequest request) {

        LearningPath pathToBeSaved = learningPathMapper.mapToLearningPath(request);
        pathToBeSaved.setStatus(LearningPathStatus.NOT_STARTED);
        LearningPath savedPath = learningPathRepository.save(pathToBeSaved);
        return learningPathMapper.mapToLearningPathResponse(savedPath);

    }

    public ResponseEntity<?> getLearningPath(Long pathId) {
        LearningPath pathFromDataBase = learningPathRepository.findById(pathId).orElse(null);
        if (Objects.nonNull(pathFromDataBase)) {
            return ResponseEntity.ok().body(learningPathMapper.mapToLearningPathResponse(pathFromDataBase));
        } else {
            return ResponseEntity.badRequest().body("No Path found for given Id");
        }
    }
}
