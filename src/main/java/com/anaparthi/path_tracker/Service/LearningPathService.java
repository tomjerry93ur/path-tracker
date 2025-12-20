package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.auth.AppUserDetailsService;
import com.anaparthi.path_tracker.auth.User;
import com.anaparthi.path_tracker.auth.UserRepository;
import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.LearningPathStatus;
import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.dto.LearningPathShort;
import com.anaparthi.path_tracker.mapper.LearningPathMapper;
import com.anaparthi.path_tracker.repository.LearningPathRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final UserRepository userRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final LearningPathMapper learningPathMapper = Mappers.getMapper(LearningPathMapper.class);


    public LearningPathResponse createLearningPath(LearningPathRequest request) {

        User user = appUserDetailsService.getCurrentUser();
        LearningPath pathToBeSaved = learningPathMapper.mapToLearningPath(request);
        pathToBeSaved.setUser(user);
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

    public ResponseEntity<?> getLearningPaths() {

        List<LearningPath> paths =
                learningPathRepository.findAll();

        if (paths.isEmpty()) {
            return ResponseEntity.badRequest().body("No learning paths found");
        }

        List<LearningPathShort> response = learningPathMapper.mapToLearningPathShort(paths);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> updateLearningPath(Long pathId,
                                                LearningPathRequest request) {

        LearningPath Path =
                learningPathRepository.findById(pathId).orElse(null);

        if (Objects.isNull(Path)) {
            return ResponseEntity.badRequest()
                    .body("No Path found for given Id");
        }

        Path.setTitle(request.getTitle());
        Path.setDescription(request.getDescription());
        Path.setStartDate(request.getStartDate());
        Path.setTargetEndDate(request.getTargetEndDate());

        LearningPath updatedPath =
                learningPathRepository.save(Path);

        return ResponseEntity.ok()
                .body(learningPathMapper.mapToLearningPathResponse(updatedPath));
    }

    public ResponseEntity<?> deleteLearningPath(Long pathId) {

        LearningPath path =
                learningPathRepository.findById(pathId).orElse(null);

        if (Objects.isNull(path)) {
            return ResponseEntity.badRequest()
                    .body("No Path found for given Id");
        }

        learningPathRepository.deleteById(pathId);

        return ResponseEntity.ok()
                .body("Learning Path deleted");
    }

    public List<LearningPathResponse> getMyLearningPaths() {

        User user = appUserDetailsService.getCurrentUser();

        return learningPathRepository.findByUserId(user.getId())
                .stream()
                .map(learningPathMapper::mapToLearningPathResponse)
                .toList();
    }
}
