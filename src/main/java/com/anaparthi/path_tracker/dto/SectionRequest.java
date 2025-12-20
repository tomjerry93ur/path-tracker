package com.anaparthi.path_tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Builder
@Setter
@Getter
@AllArgsConstructor

public class SectionRequest {

    @NotBlank
    private String title;
    private String description;
    @Min(0)
    private long orderIndex;
    private int estimatedDays;
}
