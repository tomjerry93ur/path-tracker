package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.Service.LearningPathService;
import com.anaparthi.path_tracker.domain.LearningPath;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paths")
public class LearningPathController {
    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService){
        this.learningPathService=learningPathService;
    }
    @PostMapping
    public LearningPathResponse createPath(@Valid @RequestBody LearningPathRequest request){
        return learningPathService.createLearningPath(request);
    }

    @GetMapping
    public List<LearningPathResponse>getAllPaths(){
        return learningPathService.getAllLearningPaths();
    }

    @GetMapping("/{pathId}")
    public LearningPathResponse getPath(@PathVariable Long pathId){
        return learningPathService.startLearningPath(pathId);
    }
    @PutMapping("/{pathId}")
    public LearningPathResponse updatePath(@PathVariable Long pathId, @Valid @RequestBody LearningPathRequest request){
        return learningPathService.updateLearningPath(pathId, request);
    }

    @PostMapping("/{pathId}/start")
    public LearningPathResponse startPath(@PathVariable Long pathId){
        return learningPathService.startLearningPath(pathId);
    }

    @PostMapping("/{pathId}/complete")
    public void  completePath(@PathVariable Long pathId){
        learningPathService.CompletePath(pathId);
    }
}
