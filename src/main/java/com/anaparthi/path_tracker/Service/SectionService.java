package com.anaparthi.path_tracker.Service;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.domain.SectionStatus;
import com.anaparthi.path_tracker.domain.TaskStatus;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
import com.anaparthi.path_tracker.mapper.SectionMapper;
import com.anaparthi.path_tracker.repository.LearningPathRepository;
import com.anaparthi.path_tracker.repository.SectionRepository;
import com.anaparthi.path_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final TaskRepository taskRepository;
    private final LearningPathRepository learningPathRepository;
    private final SectionMapper sectionMapper =
            Mappers.getMapper(SectionMapper.class);

    public ResponseEntity<?> createSection(Long pathId, SectionRequest request) {

        LearningPath path = learningPathRepository.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));

        Section section = sectionMapper.mapToSection(request);
        section.setPath(path);
        section.setStatus(SectionStatus.NOT_STARTED);

        path.addSection(section);
        learningPathRepository.save(path);
        return ResponseEntity.ok(sectionMapper.mapToSectionResponse(section)
        );
    }

    public ResponseEntity<?> updateSection(Long pathId,
                                           Long sectionId,
                                           SectionRequest request) {

        Section Section =
                sectionRepository.findById(sectionId).orElse(null);

        if (Objects.isNull(Section)) {
            return ResponseEntity.badRequest()
                    .body("Section not found");
        }

        Section.setTitle(request.getTitle());
        Section.setDescription(request.getDescription());
        Section.setOrderIndex(request.getOrderIndex());

        Section updatedSection =
                sectionRepository.save(Section);

        return ResponseEntity.ok(
                sectionMapper.mapToSectionResponse(updatedSection)
        );
    }

    public ResponseEntity<?> deleteSection(Long pathId,
                                           Long sectionId) {

        Section section = sectionRepository.findById(sectionId).orElse(null);

        if (Objects.isNull(section)) {
            return ResponseEntity.badRequest()
                    .body("Section not found");
        }
        sectionRepository.deleteById(sectionId);

        return ResponseEntity.ok("Section deleted");
    }

}
