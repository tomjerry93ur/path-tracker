package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.SectionService;
import com.anaparthi.path_tracker.domain.Section;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paths/{pathId}/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService){
        this.sectionService = sectionService;
    }

    @PostMapping
    public Section addSection(@PathVariable Long pathId, @Valid @RequestBody Section section){
        return sectionService.addSection(pathId, section);
    }

    @GetMapping
    public Section getSections(@PathVariable Long pathId){
        return sectionService.getSectionById(pathId);
    }

    @PutMapping("/{sectionId}")
    public Section updateSection(@PathVariable Long pathId, @PathVariable Long sectionId, @Valid @RequestBody Section section){
        return sectionService.updateSection(sectionId,section);
    }
}
