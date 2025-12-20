package com.anaparthi.path_tracker.dto;

import lombok.*;
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LearningPathShort {
    private String title;
    private String description;
    private String status;
}