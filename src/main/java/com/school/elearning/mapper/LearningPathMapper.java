package com.school.elearning.mapper;

import com.school.elearning.dto.request.LearningPathRequest;
import com.school.elearning.dto.response.LearningPathResponse;
import com.school.elearning.entity.LearningPath;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LearningPathMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    LearningPath toEntity(LearningPathRequest request);

    LearningPathResponse toResponse(LearningPath learningPath);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void updateLearningPathFromDto(LearningPathRequest request, @MappingTarget LearningPath learningPath);
}
