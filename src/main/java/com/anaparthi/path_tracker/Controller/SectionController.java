package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.SectionService;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/paths/{pathId}/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }

//    @PostMapping
//    public SectionResponse addSection(@PathVariable Long pathId, @Valid @RequestBody SectionRequest request){
//        return sectionService.addSection(pathId, request);
//    }
//
//    @GetMapping
//    public List<SectionResponse> getSections(@PathVariable Long pathId){
//        return sectionService.getSectionsById(pathId);
//    }
//
//    @PutMapping("/{sectionId}")
//    public SectionResponse updateSection(@PathVariable Long pathId, @PathVariable Long sectionId, @Valid @RequestBody SectionRequest request){
//        return sectionService.updateSection(sectionId,request);
//    }
}
