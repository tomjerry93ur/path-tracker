package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.LearningPathStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LearningPathResponse {

    private Long id;
    private String title;
    private String description;
    private LearningPathStatus status;
    private LocalDate startDate;
    private LocalDate targetEndDate;
    private List<SectionResponse> sections;

}
