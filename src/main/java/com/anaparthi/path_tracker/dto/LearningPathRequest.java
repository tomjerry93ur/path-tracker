package com.anaparthi.path_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LearningPathRequest {

    @NotBlank
    private String title;

    private String description;
    private LocalDate startDate;
    private LocalDate targetEndDate;

}
