package com.anaparthi.path_tracker.Controller;

import com.anaparthi.path_tracker.Service.SectionService;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paths/{pathId}/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<?> createSection(@PathVariable Long pathId, @Valid @RequestBody SectionRequest request) {
        return sectionService.createSection(pathId, request);
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<?> updateSection(@PathVariable Long pathId, @PathVariable Long sectionId, @Valid @RequestBody SectionRequest request) {
        return sectionService.updateSection(pathId, sectionId, request);
    }

    @DeleteMapping("/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long pathId, @PathVariable Long sectionId) {
        return sectionService.deleteSection(pathId, sectionId);
    }
}
