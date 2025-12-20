package com.anaparthi.path_tracker.mapper;

import com.anaparthi.path_tracker.domain.Section;
import com.anaparthi.path_tracker.dto.SectionRequest;
import com.anaparthi.path_tracker.dto.SectionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    Section mapToSection(SectionRequest request);

    SectionResponse mapToSectionResponse(Section section);
}
