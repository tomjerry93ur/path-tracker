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

//    @PutMapping("/{pathId}")
//    public LearningPathResponse updatePath(@PathVariable Long pathId, @Valid @RequestBody LearningPathRequest request){
//        return learningPathService.updateLearningPath(pathId, request);
//    }
//
//    @PostMapping("/{pathId}/start")
//    public LearningPathResponse startPath(@PathVariable Long pathId){
//        return learningPathService.startLearningPath(pathId);
//    }
//
//    @PostMapping("/{pathId}/complete")
//    public void  completePath(@PathVariable Long pathId){
//        learningPathService.CompletePath(pathId);
//    }
}
