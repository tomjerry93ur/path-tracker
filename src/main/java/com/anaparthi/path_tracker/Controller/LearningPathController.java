package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.Service.LearningPathService;
import com.anaparthi.path_tracker.domain.LearningPath;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paths")
@RequiredArgsConstructor
public class LearningPathController {

    private final LearningPathService learningPathService;

    @PostMapping
    public LearningPathResponse createPath(@Valid @RequestBody LearningPathRequest request){
        return learningPathService.createLearningPath(request);
    }


    @GetMapping("/{pathId}")
    public ResponseEntity<?> getPath(@PathVariable Long pathId){
        return learningPathService.getLearningPath(pathId);
    }

    @GetMapping
    public ResponseEntity<?> getLearningPaths() {
        return learningPathService.getLearningPaths();
    }

    @PutMapping("/{pathId}")
    public ResponseEntity<?> updatePath(
            @PathVariable Long pathId,
            @Valid @RequestBody LearningPathRequest request) {

        return learningPathService.updateLearningPath(pathId, request);
    }

    @DeleteMapping("/{pathId}")
    public ResponseEntity<?> deletePath(@PathVariable Long pathId) {
        return learningPathService.deleteLearningPath(pathId);
    }

    @GetMapping("/my-paths")
    public List<LearningPathResponse> getMyLearningPaths() {
        return learningPathService.getMyLearningPaths();
    }}
