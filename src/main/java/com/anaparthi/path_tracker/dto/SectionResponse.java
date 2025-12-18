package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.SectionStatus;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SectionResponse {

    private Long id;
    private String title;
    private String description;
    private Long orderIndex;
    private SectionStatus status;
    private List<TaskResponse> tasks;
}
