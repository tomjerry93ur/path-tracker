package com.anaparthi.path_tracker.Controller;

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
    public LearningPath createPath(@Valid @RequestBody LearningPath path){
        return learningPathService.createLearningPath(path);
    }

    @GetMapping
    public List<LearningPath>getAllPaths(){
        return learningPathService.getAllLearningPaths();
    }

    @GetMapping("/{pathId}")
    public LearningPath getPath(@PathVariable Long pathId){
        return learningPathService.startLearningPath(pathId);
    }
    @PutMapping("/{pathId}")
    public LearningPath updatePath(@PathVariable Long pathId, @Valid @RequestBody LearningPath path){
        return learningPathService.updateLearningPath(pathId, path);
    }

    @PostMapping("/{pathId}/start")
    public LearningPath startPath(@PathVariable Long pathId){
        return learningPathService.startLearningPath(pathId);
    }

    @PostMapping("/{pathId}/complete")
    public void  completePath(@PathVariable Long pathId){
        learningPathService.CompletePath(pathId);
    }
}
