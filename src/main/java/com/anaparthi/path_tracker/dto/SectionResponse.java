package com.anaparthi.path_tracker.dto;

import com.anaparthi.path_tracker.domain.SectionStatus;

public class SectionResponse {

    private Long id;
    private String title;
    private String description;
    private Long orderIndex;
    private SectionStatus status;

    public SectionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public SectionStatus getStatus() {
        return status;
    }

    public void setStatus(SectionStatus status) {
        this.status = status;
    }

    public void setEstimatedDays(int estimatedDays) {
    }
}
