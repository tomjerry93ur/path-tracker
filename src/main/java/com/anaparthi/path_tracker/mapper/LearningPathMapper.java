package com.anaparthi.path_tracker.mapper;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LearningPathMapper {

    LearningPathResponse mapToLearningPathResponse(LearningPath learningPath);

    LearningPath mapToLearningPath(LearningPathRequest learningPathRequest);

}
