package com.anaparthi.path_tracker.mapper;

import com.anaparthi.path_tracker.domain.LearningPath;
import com.anaparthi.path_tracker.dto.LearningPathRequest;
import com.anaparthi.path_tracker.dto.LearningPathResponse;
import com.anaparthi.path_tracker.dto.LearningPathShort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LearningPathMapper {

    LearningPathResponse mapToLearningPathResponse(LearningPath learningPath);

    LearningPath mapToLearningPath(LearningPathRequest learningPathRequest);

    LearningPathShort mapToLearningPathShort(LearningPath learningPath);

    List<LearningPathShort> mapToLearningPathShort(List<LearningPath> paths);
}
