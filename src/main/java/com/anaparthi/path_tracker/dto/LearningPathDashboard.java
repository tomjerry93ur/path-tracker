package com.anaparthi.path_tracker.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LearningPathDashboard {
    String totalPaths;
    String pathsInProgress;
    String completedPaths;
    String averageProgress;
    List<LearningPathShort> learningPaths;
}